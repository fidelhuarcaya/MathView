package dev.fidelhuarcaya.mathview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.core.content.ContextCompat;

/**
 * Modified by Fidel Huarcaya
 */

public class MathView extends WebView {
    private final String TAG = "KhanAcademyKatexView";
    private static final float default_text_size = 18;
    private String text;
    private int text_color;
    private int text_size;
    private boolean clickable = false;
    private boolean enable_zoom_in_controls = false;
    private boolean text_center = false;


    public MathView(Context context) {
        super(context);
        configurationSettingWebView(enable_zoom_in_controls);
        setDefaultTextColor(context);
        setDefaultTextSize();
    }


    public MathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        configurationSettingWebView(enable_zoom_in_controls);
        TypedArray mTypeArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.MathView,
                0, 0);
        try {
            setBackgroundColor(mTypeArray.getInteger(R.styleable.MathView_setViewBackgroundColor, ContextCompat.getColor(context, android.R.color.transparent)));
            setTextColor(mTypeArray.getColor(R.styleable.MathView_setTextColor, ContextCompat.getColor(context, android.R.color.black)));
            pixelSizeConversion(mTypeArray.getDimension(R.styleable.MathView_setTextSize, default_text_size));
            setText(mTypeArray.getString(R.styleable.MathView_setText));
            setClickable(mTypeArray.getBoolean(R.styleable.MathView_setClickable, false));
            setTextCenter(mTypeArray.getBoolean(R.styleable.MathView_setTextCenter, false));

        } catch (Exception e) {
            Log.d(TAG, "Exception:" + e);
        } finally {
            mTypeArray.recycle();
        }


    }

    private void setTextCenter(boolean textCenter) {
        this.text_center = textCenter;
        loadData();
    }

    public void setViewBackgroundColor(int color) {
        setBackgroundColor(color);
        this.invalidate();
    }

    private void pixelSizeConversion(float dimension) {
        /*if (Build.VERSION.SDK_INT < 29) {
            if (dimension == default_text_size)
                setTextSize((int) default_text_size);
            else {
                Toast.makeText(getContext(), "dim: " + (dimension / 2.4), Toast.LENGTH_SHORT).show();
                setTextSize((int) (dimension / 2.4));
            }
        } else */
        if (dimension == default_text_size) {
            setTextSize((int) default_text_size);
        } else {
            setTextSize((int) (dimension / 1.4));
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void configurationSettingWebView(boolean enable_zoom_in_controls) {
        this.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        WebSettings settings = this.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);

        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);

        this.setVerticalScrollBarEnabled(enable_zoom_in_controls);
        this.setHorizontalScrollBarEnabled(enable_zoom_in_controls);
        Log.d(TAG, "Zoom in controls:" + enable_zoom_in_controls);

    }


    public void setText(String formula_text) {
        this.text = formula_text;
        loadData();
    }


    private String getOfflineKatexConfig() {
        String offline_config = "<!DOCTYPE html>\n" +
                "<html >\n" +
                "    <head>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <title>Auto-render test</title>\n" +
                "        <link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/katex/katex.min.css\">\n" +
                "        <link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/themes/style.css\" >\n" +
                "        <script type=\"text/javascript\" src=\"file:///android_asset/katex/katex.min.js\" ></script>\n" +
                "        <script type=\"text/javascript\" src=\"file:///android_asset/katex/contrib/auto-render.min.js\" ></script>\n" +
                "        <script type=\"text/javascript\" src=\"file:///android_asset/katex/contrib/auto-render.js\" ></script>\n" +
                "        <script type=\"text/javascript\" src=\"file:///android_asset/jquery.min.js\" ></script>\n" +
                "        <script type=\"text/javascript\" src=\"file:///android_asset/latex_parser.js\" ></script>\n" +

                "        <script type=\"text/javascript\" src=\"file:///android_asset/katex/katex_config.js\" ></script>\n" +

                //width=device-width

                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=2, user-scalable=yes\"/>\n" +
                "<link rel=\"stylesheet\" href=\"file:///android_asset/webviewstyle.css\"/>\n" +
                "<style type='text/css'>" +
                "body {" +
                "margin: 0px;" +
                "padding: 0px;" +
                getAlignText() +
                "font-size:" + this.text_size + "px;" +
                "color:" + getHexColor(this.text_color) + ";" +
                " }" +
                " </style>" +
                "    </head>\n" +
                "    <body>\n" +
                "        {formula}\n" +
                "    </body>\n" +
                "</html>";
    /*String start = "<html><head><meta http-equiv='Content-Type' content='text/html' charset='UTF-8' /><style> body {"+
        " white-space: nowrap;}</style></head><body>";

    String end = "</body></html>";*/
        return offline_config.replace("{formula}", this.text);


    }

    public String getAlignText() {
        return text_center ? "text-align:center;" : "";
    }

    public void setTextSize(int size) {
        this.text_size = size;
        loadData();

    }

    public void setTextColor(int color) {

        this.text_color = color;
        loadData();
    }

    private String getHexColor(int intColor) {
        //Android and javascript color format differ javascript support Hex color, so the android color which user sets is converted to hexcolor to replicate the same in javascript.
        String hexColor = String.format("#%06X", (0xFFFFFF & intColor));
        Log.d(TAG, "Hex Color:" + hexColor);
        return hexColor;
    }


    private void setDefaultTextColor(Context context) {
        //sets default text color to black
        this.text_color = ContextCompat.getColor(context, android.R.color.black);

    }

    private void setDefaultTextSize() {
        //sets view default text size to 18
        this.text_size = (int) default_text_size;
    }

    private void loadData() {
        if (this.text != null) {
            this.loadDataWithBaseURL("null", getOfflineKatexConfig(), "text/html", "UTF-8", "about:blank");
        }

    }

    public void setClickable(boolean is_clickable) {
        this.setEnabled(true);
        this.clickable = is_clickable;
        this.enable_zoom_in_controls = !is_clickable;
        configurationSettingWebView(this.enable_zoom_in_controls);
        this.invalidate();
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.clickable && event.getAction() == MotionEvent.ACTION_DOWN) {
            this.callOnClick();
            return false;
        } else {
            return super.onTouchEvent(event);
        }
    }

}

