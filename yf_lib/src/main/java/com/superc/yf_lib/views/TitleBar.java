package com.superc.yf_lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.superc.yf_lib.R;


/**
 * 自定义的TitleBar高度最好为70且为沉浸式
 * mTitle  标题名称     mTitle_size  标题文字大小默认18
 * mLeft_text  左侧文字  mLeft_img 左侧图片(不传则没有，传即显示)
 * mRight_text 右侧文字  mRight_img 右侧图片(不传则没有，传即显示)
 * titlebar_color  所有标题栏文字的颜色
 * other_size      除中间标题外其余文字的大小默认14
 *注意: PADDINGTOP      使用沉浸式的时候距离顶部的高度设置默认为50如果没有的话改成10，需要在xml中的TitleBar的高度至少为70
 */

public class TitleBar extends RelativeLayout {

    private TextView mtv_title, mtv_left, mtv_right;

    private String mTitle;
    private String mLeft_text;
    private String mRight_text;
    private int mLeft_img;
    private int mRight_img;
    private int mTitle_size;
    private int titlebar_color;
    private int other_size;

    private LayoutParams mleft_params, mcenter_params, mright_params;

    public static final int PADDINGTOP = 50;

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.mytitle);
        mTitle = typedArray.getString(R.styleable.mytitle_title);
        mTitle_size = typedArray.getInt(R.styleable.mytitle_title_size, 18);
        mLeft_text = typedArray.getString(R.styleable.mytitle_left_text);
        mLeft_img = typedArray.getResourceId(R.styleable.mytitle_left_img, 0);
        mRight_text = typedArray.getString(R.styleable.mytitle_right_text);
        mRight_img = typedArray.getResourceId(R.styleable.mytitle_right_img, 0);
        other_size = typedArray.getInt(R.styleable.mytitle_other_size, 14);
        titlebar_color = typedArray.getInt(R.styleable.mytitle_title_bar_color, R.color.white);

        typedArray.recycle();
        mtv_title = new TextView(context);
        mtv_left = new TextView(context);
        mtv_right = new TextView(context);

        mtv_left.setText(mLeft_text);
        mtv_title.setText(mTitle);
        mtv_right.setText(mRight_text);
        /*左侧图片  左侧文字*/
        mleft_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mleft_params.addRule(CENTER_VERTICAL);
        mleft_params.addRule(ALIGN_PARENT_LEFT);
        mtv_left.setLayoutParams(mleft_params);
        mtv_left.setGravity(Gravity.CENTER_VERTICAL);
        mtv_left.setPadding(15, PADDINGTOP, 10, 10);
        mtv_left.setTextColor(context.getResources().getColor(titlebar_color));
        mtv_left.setTextSize(other_size);
        if (mLeft_img != 0) {
            Drawable drawableLeft = getResources().getDrawable(mLeft_img);
            mtv_left.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
            mtv_left.setCompoundDrawablePadding(7);
        }

        /*标题文字*/
        mcenter_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mcenter_params.addRule(CENTER_IN_PARENT);
        mtv_title.setLayoutParams(mcenter_params);
        mtv_title.setGravity(Gravity.CENTER);
        mtv_title.setTextSize(mTitle_size);
        mtv_title.setPadding(0, PADDINGTOP, 0, 0);
        mtv_title.setTextColor(context.getResources().getColor(titlebar_color));


        /*右侧文字*/
        mright_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mright_params.addRule(CENTER_VERTICAL);
        mright_params.addRule(ALIGN_PARENT_RIGHT);
        mtv_right.setLayoutParams(mright_params);
        mtv_right.setGravity(Gravity.CENTER_VERTICAL);
        mtv_right.setPadding(10, PADDINGTOP, 15, 10);
        mtv_right.setTextColor(context.getResources().getColor(titlebar_color));
        mtv_right.setTextSize(other_size);
        if (mRight_img != 0) {
            Drawable drawableLeft = getResources().getDrawable(mRight_img);
            mtv_right.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
            mtv_right.setCompoundDrawablePadding(4);
        }


        addView(mtv_left);
        addView(mtv_title);
        addView(mtv_right);
    }

    /*点击左侧TextView时的监听*/
    public void setOnLeftViewClickListener(final OnViewClickListener clickListener) {
        mtv_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.OnViewClickListener();
                }
            }
        });
    }

    /*点击右侧TextView时的监听*/
    public void setOnRightViewClickListener(final OnViewClickListener clickListener) {
        mtv_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.OnViewClickListener();
                }
            }
        });
    }

    /*点击中间TextView时的监听(如果有必要的话)*/
    public void setOnTitleViewClickListener(final OnViewClickListener clickListener) {
        mtv_title.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.OnViewClickListener();
                }
            }
        });
    }

    public interface OnViewClickListener {
        void OnViewClickListener();
    }

}
