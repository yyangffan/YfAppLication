package com.superc.yf_lib.base;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.superc.yf_lib.R;
import com.superc.yf_lib.utils.ToastUtil;

/********************************************************************
  @version: 1.0.0
  @description: 集成了 nohttp Gson ButterKnife EventBus
  @author: user
  @time: 2018/3/30 11:05
  @变更历史:
********************************************************************/

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private PopupWindow mPopupWindow;

    /**
     * @param msg 弹出提示类型--只支持String和Int索引
     */
    public void showToast(Object msg) {
        if (msg instanceof String) {
            ToastUtil.showToast(this.getActivity(), (String) msg);
        } else {
            ToastUtil.showToast(this.getActivity(), (int) msg);
        }
    }

    /**
     * @param cls 跳转到的Activity名称class
     */
    public void stActivity(Class cls) {
        stActivity(cls,null);
    }

    /**
     * @param bundle 跳转时携带的数据
     * @param cls    跳转到的Activity名称class
     */
    public void stActivity(Class cls, Bundle bundle) {
        Intent intent = new Intent(this.getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public abstract void init();

    /*加载Loading 如下两个*/
    public void showLoadPop() {
        mPopupWindow = new PopupWindow(LayoutInflater.from(this.getActivity()).inflate(R.layout.layout_load_popup, null),
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(this.getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setFocusable(true);

    }

    public void hideLoadPop() {
        if(mPopupWindow!=null){
            mPopupWindow.dismiss();
        }
    }
}
