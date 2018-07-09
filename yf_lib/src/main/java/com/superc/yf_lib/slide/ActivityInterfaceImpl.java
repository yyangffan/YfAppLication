package com.superc.yf_lib.slide;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**  关闭侧滑退出setSlideable(false);
 * 这个包里面的是进行左侧返回使用的
 * 这个类用来管理 activity 的栈
 * @since 2016/10/28
 */
class ActivityInterfaceImpl extends AppCompatActivity implements ActivityInterface {

    private Application.ActivityLifecycleCallbacks mActivityLifecycleCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityStackManager.addToStack(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityStackManager.removeFromStack(this);

        if (mActivityLifecycleCallbacks != null) {
            mActivityLifecycleCallbacks.onActivityDestroyed(this);
        }
    }

    @Override
    public void setActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callbacks) {
        mActivityLifecycleCallbacks = callbacks;
    }
}
