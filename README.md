# YfAppLication
<h2>自己的库用来方便开发</h2>
<h3>使用方法：</h3>
<h4>1.在主工程中添加：</h4>
&emsp;allprojects {<br>
&emsp;&emsp;repositories {<br>
&emsp;&emsp;&emsp;...<br>
&emsp;&emsp;&emsp;maven { url 'https://jitpack.io' }<br>
&emsp;&emsp;}<br>
&emsp;}<br>
<h4>2.在项目工程中添加：</h4>
&emsp;dependencies {<br>
&emsp;&emsp;&emsp;implementation 'com.github.yyangffan:YfAppLication:v1.0.0'<br>
&emsp;}<br>
<h4>如果studio版本不是最新就将implementation改成compile</h4>
<h4>目前使用到的依赖有</h4>
&emsp;&emsp;compile 'com.yanzhenjie.nohttp:nohttp:1.1.9'<br>
&emsp;&emsp;compile 'com.google.code.gson:gson:2.8.2'<br>
&emsp;&emsp;compile 'org.greenrobot:eventbus:3.0.0'<br>
&emsp;&emsp;compile 'com.wang.avi:library:2.1.3'<br>
