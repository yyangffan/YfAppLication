package com.superc.yf_lib.views.spec_view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/********************************************************************
  @version: 1.0.0
  @description: 用来进行规格显示的自定义View，如果一行的规格数放不下的时候就自动换行
  @author: 杨帆
  @time: 2018/8/6 17:28
  @变更历史: 其实这个View无关紧要主要还是FlowLayoutManager，使用方法
             FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
            mRecyclerView.setLayoutManager(flowLayoutManager);
 其它在xml以及Adapter的写法是一样的
********************************************************************/

public class NestedRecyclerView extends RecyclerView {
    public NestedRecyclerView(Context context) {
        super(context);
    }

    public NestedRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        FlowLayoutManager layoutManager = (FlowLayoutManager)this.getLayoutManager();
        int widthMode = View.MeasureSpec.getMode(widthSpec);
        int measureWidth = View.MeasureSpec.getSize(widthSpec);
        int heightMode = View.MeasureSpec.getMode(heightSpec);
        int measureHeight = View.MeasureSpec.getSize(heightSpec);
        int width;
        if(widthMode == 1073741824) {
            width = measureWidth;
        } else {
            width = this.getContext().getResources().getDisplayMetrics().widthPixels;
        }

        int height;
        if(heightMode == 1073741824) {
            height = measureHeight;
        } else {
            height = layoutManager.getTotalHeight() + this.getPaddingTop() + this.getPaddingBottom();
        }

        this.setMeasuredDimension(width, height);
    }
}
