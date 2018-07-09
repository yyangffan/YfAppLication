package com.superc.yf_lib.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.superc.yf_lib.R;

/**
 * 弹窗工具类
 */

public class ShowRemindDialog {
    private TextView mtv_title;
    private TextView mtv_content;
    private TextView mtv_left;
    private TextView mtv_right;
    private ImageView mimgv;
    private View mV;
    private Context mContext;
    private AlertDialog mAlertDialog;


    public ShowRemindDialog(Context context) {
        mContext = context;
        mAlertDialog = new AlertDialog.Builder(mContext).create();
        mV = LayoutInflater.from(mContext).inflate(R.layout.dialog_remind, null);
        mAlertDialog.setView(mV);
        mAlertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initViews();

    }

    private void initViews() {
        mtv_title = mV.findViewById(R.id.remind_dialog_title);
        mtv_content = mV.findViewById(R.id.remind_dialog_content);
        mtv_left = mV.findViewById(R.id.remind_dialog_left);
        mtv_right = mV.findViewById(R.id.remind_dialog_right);
        mimgv = mV.findViewById(R.id.remind_dialog_imgv);
    }


    /**
     * @param title    上方显示的标题----------不为“”就显示
     * @param content  中间显示的内容----------不为“”就显示
     * @param left     左侧按钮内容------------不为“”就显示
     * @param right    右侧按钮内容------------不为“”就显示
     * @param imgv_id  中间显示的图片----------不为0就显示
     * @param listener 点击下方按钮的监听
     * @return
     */
    public ShowRemindDialog showDialog(String title, String content, String left, String right, int imgv_id, final OnRemindTextClickListener listener) {
        setText(mtv_content,content);
        setText(mtv_title,title);
        setText(mtv_left,left);
        setText(mtv_right,right);
        if (imgv_id != 0) {
            mimgv.setImageResource(imgv_id);
        }
        mtv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.OnLeftTxtClickLisntener();
                }
                mAlertDialog.dismiss();
            }
        });
        mtv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.OnRightTxtClickListener();
                }
                mAlertDialog.dismiss();
            }
        });
        mAlertDialog.show();
        return this;
    }

    /**
     * 底部字体颜色
     *
     * @param left_color
     * @param right_color
     * @return
     */
    public ShowRemindDialog setBotWenZiColor(int left_color, int right_color) {
        mtv_left.setTextColor(left_color == 0 ? Color.parseColor("#000000") : mContext.getResources().getColor(left_color));
        mtv_right.setTextColor(right_color == 0 ? Color.parseColor("#000000") : mContext.getResources().getColor(right_color));
        return this;
    }

    /**
     * 是否点击外侧消失
     *
     * @param canDis true可以  false不可以
     */
    public ShowRemindDialog setTouchCancel(boolean canDis) {
        mAlertDialog.setCancelable(canDis);
        return this;
    }

    /*根据传入参数是否为“”进行展示隐藏部分控件*/
    private void setText(TextView text, String content) {
        if (!content.equals("")) {
            text.setText(content);
        } else {
            text.setVisibility(View.GONE);
        }
    }


    /*点击下方按钮的监听*/
    public abstract static class OnRemindTextClickListener {
        public void OnLeftTxtClickLisntener() {
        }

        ;

        public void OnRightTxtClickListener() {
        }

        ;
    }

}
