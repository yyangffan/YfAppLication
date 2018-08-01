# YfAppLication
自己的库用来方便开发<br>
使用方法：\<br>
1.在主工程中添加：
  allprojects {
  		repositories {
	  		...
	  		maven { url 'https://jitpack.io' }
	  	}
	  }
 2.在项目工程中添加：
    dependencies {
	        implementation 'com.github.yyangffan:YfAppLication:v1.0.0'
	  }
