package dev.fidelhuarcaya.mathview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MathView mathView= findViewById(R.id.math_view);
        mathView.setText(sampleText());

    }

    private String sampleText(){
        return "<b>Simple formula:<br><br>" +
                "$x=\\dfrac{-b\\pm\\sqrt{b -4ac}}{2a}$"+


                "<br><br> Align Center formula: <br><br>"+
                "$$f(\\relax{x}) = \\int_{-\\infty}^\\infty\n" +
                "    f(\\hat\\xi)\\,e^{2 \\pi i \\xi x}\n" +
                "    \\,d\\xi$$"+

                "<br><br> Boxed formula: <br><br>" +
                "$\\boxed{F=m \\cdot a}$" +



                "<br><br> Set background color: <br><br>" +
                "<div style=\"background-color:red;padding:1rem;margin:2rem;\"" +
                ">$c = \\pm\\sqrt{a^2 + b^2}$</div>" +


                "<br><br> More samples: <br><br>" +
                "$c^2 = a^2 + b^2$ <br><br>" +
                "$(a+b)^2=a^2+2ab+b^2$ <br><br>" +
                "$\\dfrac{sin2x}{1+cos(2x)}$ <br><br>" +
                "$$ <br><br>";
    }
}