# YfAppLication
<h3>自己的库用来方便开发</h3>
<h4>使用方法：</h4>
<h5>1.在主工程中添加：</h5>
&emsp;allprojects {<br>
&emsp;&emsp;repositories {<br>
&emsp;&emsp;&emsp;...<br>
&emsp;&emsp;&emsp;maven { url 'https://jitpack.io' }<br>
&emsp;&emsp;}<br>
&emsp;}<br>
<h5>2.在项目工程中添加：</h5>
&emsp;dependencies {<br>
&emsp;&emsp;&emsp;implementation 'com.github.yyangffan:YfAppLication:v1.0.0'<br>
&emsp;}<br>
<h5 color="#ff0000">如果studio版本不是最新就将implementation改成compile</h5>
