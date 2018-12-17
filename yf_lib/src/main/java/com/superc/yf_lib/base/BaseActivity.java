package com.superc.yf_lib.base;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.superc.yf_lib.R;
import com.superc.yf_lib.slide.SlideBackActivity;
import com.superc.yf_lib.utils.ToastUtil;


/********************************************************************
 @version: 1.0.0
 @description:集成了nohttp、Gson、ButterKnife、EventBus
 @author:呵呵。
 @time:2018/3/30、11:05
 @变更历史:如果要去掉右滑返回上一界面使用setSlideable(false);添加到setContentView(R.layout.xxxxx);下面
 ********************************************************************/

public abstract class BaseActivity extends SlideBackActivity implements View.OnClickListener {
    private ViewGroup mRootView;
    private TextView mtv_title;
    private ImageView mimgv_back;
    private LinearLayout mll_right;
    private TextView mtv_right;
    private ImageView mimgv_small;
    private ImageView mimgv_big;
    private RelativeLayout mrela;
    private PopupWindow mPopupWindow;

    @Override
    public void setContentView(int layoutResID) {
        setContentView(LayoutInflater.from(this).inflate(layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        mRootView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.action_bar_activity_layout, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.BELOW, R.id.title_bar_container);
        mRootView.addView(view, 1, params);
        super.setContentView(mRootView);
        mtv_title = (TextView) findViewById(R.id.title_text_center);
        mtv_right = (TextView) findViewById(R.id.title_text_right);
        mimgv_back = (ImageView) findViewById(R.id.left_back_image_view);
        mimgv_small = (ImageView) findViewById(R.id.title_imgv_samll);
        mimgv_big = (ImageView) findViewById(R.id.title_imgv_big);
        mll_right = (LinearLayout) findViewById(R.id.title_ll_small);
        mrela = (RelativeLayout) findViewById(R.id.title_rela);
        initListener();
    }

    /**
     * @param user_titleBar false-->不使用标题栏
     */
    public void setUser_titleBar(boolean user_titleBar) {
        if(!user_titleBar){
            findViewById(R.id.title_bar_container).setVisibility(View.GONE);
        }
    }

    /*界面绘制完成执行该方法*/
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        init();
    }

    public abstract void init();

    /**
     * @param title           标题名称
     * @param showBack        是否显示back   true显示  false隐藏
     * @param right_content   右侧展示的字
     * @param sm_right_imgid  右侧展示的小图片--跟文字是一起的
     * @param big_right_imgid 右侧展示的小图片--跟文字分离
     * @param rightViewClick  点击右侧的监听事件  smclik  bigclik
     */

    public void configTitle(Object title, boolean showBack, final Object right_content, int sm_right_imgid, int big_right_imgid,
                            final OnRightViewClickListener rightViewClick) {
        setContent(title, mtv_title);
        setContent(right_content, mtv_right);
        mimgv_back.setVisibility(showBack ? View.VISIBLE : View.GONE);
        mimgv_small.setVisibility(sm_right_imgid == 0 ? View.GONE : View.VISIBLE);
        mimgv_big.setVisibility(big_right_imgid == 0 ? View.GONE : View.VISIBLE);
        if (sm_right_imgid != 0) {
            mimgv_small.setImageResource(sm_right_imgid);
        }
        if (big_right_imgid != 0) {
            mimgv_big.setImageResource(big_right_imgid);
        }
        if (right_content != null || sm_right_imgid != 0) {
            mll_right.setVisibility(View.VISIBLE);
        }
        mll_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rightViewClick != null) {
                    rightViewClick.OnSmallClickListener();
                }
            }
        });
        mimgv_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rightViewClick != null) {
                    rightViewClick.OnBigClickListener();
                }
            }
        });
    }

    /**
     * 初始化标题以及是否显示左侧返回
     *
     * @param title    左侧标题
     * @param showBack 是否显示返回。
     */
    public void configeSimpleTitle(Object title, boolean showBack) {
        configTitle(title, showBack, "", 0, 0, null);
    }


    private void setContent(Object str, TextView tv) {
        if (str instanceof String) {
            tv.setText((String) str);
            tv.setVisibility((String) str == null ? View.GONE : View.VISIBLE);
        } else if (str instanceof Integer) {
            tv.setText((Integer) str);
            tv.setVisibility((Integer) str == 0 ? View.GONE : View.VISIBLE);
        } else {
            tv.setText((CharSequence) str);
            tv.setVisibility(str == null ? View.GONE : View.VISIBLE);
        }
    }

    private void initListener() {
        mimgv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseActivity.this.finish();
            }
        });
    }

    /**
     * @param cls 跳转到的Activity名称class
     */
    public void stActivity(Class cls) {
        stActivity(cls, null);
    }

    /**
     * @param bundle 跳转时携带的数据
     * @param cls    跳转到的Activity名称class
     */
    public void stActivity(Class cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * @param msg 弹出提示类型--只支持String和Int索引
     */
    public void ToastShow(Object msg) {
        if (msg instanceof String) {
            ToastUtil.showToast(this, (String) msg);
        } else {
            ToastUtil.showToast(this, (int) msg);
        }
    }

    /*加载Loading 如下两个*/
    public void showLoadPop() {
        mPopupWindow = new PopupWindow(LayoutInflater.from(this).inflate(R.layout.layout_load_popup, null),
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setFocusable(true);

    }

    public void hideLoadPop() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }

    public abstract class OnRightViewClickListener {
        public void OnSmallClickListener() {
        }

        ;

        public void OnBigClickListener() {
        }

        ;
    }


}
