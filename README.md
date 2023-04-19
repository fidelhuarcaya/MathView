<img alt="JitPack" src="https://img.shields.io/jitpack/version/com.github.fidelhuarcaya/MathView?color=gree&label=MathView&logoColor=green">

**<h1 >MathView</h1>**
To get a Git project into your build:

**Step 1. Add the JitPack repository to your build file**

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```


**Step 2. Add the dependency**
```
	dependencies {
	        implementation 'com.github.fidelhuarcaya:MathView:1.0'
	}
```



## Usage

#### Add `MathView` in your layout

```xml
    <dev.fidelhuarcaya.mathview.MathView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/math_view">

    </dev.fidelhuarcaya.mathview.MathView>
```

#### Get an instance of it in your code
```java
       MathView mathView= findViewById(R.id.math_view);
        mathView.setText("$x=\dfrac{-b\pm\sqrt{b -4ac}}{2a}$");
```

## Screenshot
<img src="screens/img.png" width="270px" height="480px" />

## How to

To learn how to write math equations in it, please have a look at [Katex](https://katex.org/l).



License
=======
     
     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
