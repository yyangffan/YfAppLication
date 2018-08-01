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
&emsp;&emsp;&emsp;implementation 'com.github.yyangffan:YfAppLication:v1.0.2'<br>
&emsp;}<br>
如果studio版本不是最新就将implementation改成compile<br>
<h4>3.目前使用到的依赖有</h4>
&emsp;&emsp;compile 'com.yanzhenjie.nohttp:nohttp:1.1.9'<br>
&emsp;&emsp;compile 'com.google.code.gson:gson:2.8.2'<br>
&emsp;&emsp;compile 'org.greenrobot:eventbus:3.0.0'<br>
&emsp;&emsp;compile 'com.wang.avi:library:2.1.3'<br>
<h4>更新说明</h4>
&emsp;&emsp;1.在项目的colors中添加main_color来控制标题栏颜色<br>
&emsp;&emsp;2.BaseActivity中添加setUseTitle,如果设置false则不使用默认标题栏<br>


<h3>问题记录</h3>
因为使用到了沉浸式直接将标题栏设为透明需要设置如下：<br>
1. 设置Manifest的主题为如下<br><h5>
&emsp;<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar"><br>
&emsp;&emsp;&lt;item name=&quot;colorPrimary&quot;&gt;@color/colorPrimary&lt;/item&gt;<br>
&emsp;&emsp;&lt;item name=&quot;colorPrimaryDark&quot;&gt;@color/colorPrimaryDark&lt;/item&gt;<br>
&emsp;&emsp;&lt;item name=&quot;colorAccent&quot;&gt;@color/colorAccent&lt;/item&gt;<br>
&emsp;&emsp;&lt;item name=&quot;android:windowTranslucentStatus&quot;&gt;true&lt;/item&gt;<br>
&emsp;</style><br></h5>
2.如果要完成底部布局可上移需要如下设置：<br>
在Manifest的Activity中添加
<h5>android:windowSoftInputMode="adjustResize|stateHidden"</h5>
还需要在该界面的顶部布局添加如下一个属性才可以:
<h5>android:fitsSystemWindows="true"</h5>


