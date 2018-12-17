package com.superc.yf_lib.views.spec_view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/********************************************************************
 @version: 1.0.0
 @description: 用来进行规格显示的自定义View，如果一行的规格数放不下的时候就自动换行
 @author: 杨帆
 @time: 2018/8/6 17:28
 @变更历史:
 ********************************************************************/

public class FlowLayoutManager extends RecyclerView.LayoutManager {
    private static final String TAG = FlowLayoutManager.class.getSimpleName();
    final FlowLayoutManager self = this;
    protected int width;
    protected int height;
    private int left;
    private int top;
    private int right;
    private int usedMaxWidth;
    private int verticalScrollOffset = 0;
    protected int totalHeight = 0;
    private FlowLayoutManager.Row row = new FlowLayoutManager.Row();
    private List<Row> lineRows = new ArrayList();
    private SparseArray<Rect> allItemFrames = new SparseArray();

    public int getTotalHeight() {
        return this.totalHeight;
    }

    public FlowLayoutManager() {
        this.setAutoMeasureEnabled(true);
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.d(TAG, "onLayoutChildren");
        this.totalHeight = 0;
        int cuLineTop = this.top;
        int cuLineWidth = 0;
        int maxHeightItem = 0;
        this.row = new FlowLayoutManager.Row();
        this.lineRows.clear();
        this.allItemFrames.clear();
        this.removeAllViews();
        if(this.getItemCount() == 0) {
            this.detachAndScrapAttachedViews(recycler);
            this.verticalScrollOffset = 0;
        } else if(this.getChildCount() != 0 || !state.isPreLayout()) {
            this.detachAndScrapAttachedViews(recycler);
            if(this.getChildCount() == 0) {
                this.width = this.getWidth();
                this.height = this.getHeight();
                this.left = this.getPaddingLeft();
                this.right = this.getPaddingRight();
                this.top = this.getPaddingTop();
                this.usedMaxWidth = this.width - this.left - this.right;
            }

            for(int i = 0; i < this.getItemCount(); ++i) {
                Log.d(TAG, "index:" + i);
                View childAt = recycler.getViewForPosition(i);
                if(8 != childAt.getVisibility()) {
                    this.measureChildWithMargins(childAt, 0, 0);
                    int childWidth = this.getDecoratedMeasuredWidth(childAt);
                    int childHeight = this.getDecoratedMeasuredHeight(childAt);
                    int itemLeft;
                    Rect frame;
                    if(cuLineWidth + childWidth <= this.usedMaxWidth) {
                        itemLeft = this.left + cuLineWidth;
                        frame = (Rect)this.allItemFrames.get(i);
                        if(frame == null) {
                            frame = new Rect();
                        }

                        frame.set(itemLeft, cuLineTop, itemLeft + childWidth, cuLineTop + childHeight);
                        this.allItemFrames.put(i, frame);
                        cuLineWidth += childWidth;
                        maxHeightItem = Math.max(maxHeightItem, childHeight);
                        this.row.addViews(new FlowLayoutManager.Item(childHeight, childAt, frame));
                        this.row.setCuTop((float)cuLineTop);
                        this.row.setMaxHeight((float)maxHeightItem);
                    } else {
                        this.formatAboveRow();
                        cuLineTop += maxHeightItem;
                        this.totalHeight += maxHeightItem;
                        itemLeft = this.left;
                        frame = (Rect)this.allItemFrames.get(i);
                        if(frame == null) {
                            frame = new Rect();
                        }

                        frame.set(itemLeft, cuLineTop, itemLeft + childWidth, cuLineTop + childHeight);
                        this.allItemFrames.put(i, frame);
                        cuLineWidth = childWidth;
                        maxHeightItem = childHeight;
                        this.row.addViews(new FlowLayoutManager.Item(childHeight, childAt, frame));
                        this.row.setCuTop((float)cuLineTop);
                        this.row.setMaxHeight((float)childHeight);
                    }

                    if(i == this.getItemCount() - 1) {
                        this.formatAboveRow();
                        this.totalHeight += maxHeightItem;
                    }
                }
            }

            this.totalHeight = Math.max(this.totalHeight, this.getVerticalSpace());
            Log.d(TAG, "onLayoutChildren totalHeight:" + this.totalHeight);
            this.fillLayout(recycler, state);
        }
    }

    private void fillLayout(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if(!state.isPreLayout()) {
            Rect displayFrame = new Rect(this.getPaddingLeft(), this.getPaddingTop() + this.verticalScrollOffset, this.getWidth() - this.getPaddingRight(), this.verticalScrollOffset + (this.getHeight() - this.getPaddingBottom()));

            for(int j = 0; j < this.lineRows.size(); ++j) {
                FlowLayoutManager.Row row = (FlowLayoutManager.Row)this.lineRows.get(j);
                float lineTop = row.cuTop;
                float lineBottom = lineTop + row.maxHeight;
                List views;
                int i;
                View scrap;
                if(lineTop < (float)displayFrame.bottom && (float)displayFrame.top < lineBottom) {
                    views = row.views;

                    for(i = 0; i < views.size(); ++i) {
                        scrap = ((FlowLayoutManager.Item)views.get(i)).view;
                        this.measureChildWithMargins(scrap, 0, 0);
                        this.addView(scrap);
                        Rect frame = ((FlowLayoutManager.Item)views.get(i)).rect;
                        this.layoutDecoratedWithMargins(scrap, frame.left, frame.top - this.verticalScrollOffset, frame.right, frame.bottom - this.verticalScrollOffset);
                    }
                } else {
                    views = row.views;

                    for(i = 0; i < views.size(); ++i) {
                        scrap = ((FlowLayoutManager.Item)views.get(i)).view;
                        this.removeAndRecycleView(scrap, recycler);
                    }
                }
            }

        }
    }

    private void formatAboveRow() {
        List views = this.row.views;

        for(int i = 0; i < views.size(); ++i) {
            FlowLayoutManager.Item item = (FlowLayoutManager.Item)views.get(i);
            View view = item.view;
            int position = this.getPosition(view);
            if((float)((Rect)this.allItemFrames.get(position)).top < this.row.cuTop + (this.row.maxHeight - (float)((FlowLayoutManager.Item)views.get(i)).useHeight) / 2.0F) {
                Rect frame = (Rect)this.allItemFrames.get(position);
                if(frame == null) {
                    frame = new Rect();
                }

                frame.set(((Rect)this.allItemFrames.get(position)).left, (int)(this.row.cuTop + (this.row.maxHeight - (float)((FlowLayoutManager.Item)views.get(i)).useHeight) / 2.0F), ((Rect)this.allItemFrames.get(position)).right, (int)(this.row.cuTop + (this.row.maxHeight - (float)((FlowLayoutManager.Item)views.get(i)).useHeight) / 2.0F + (float)this.getDecoratedMeasuredHeight(view)));
                this.allItemFrames.put(position, frame);
                item.setRect(frame);
                views.set(i, item);
            }
        }

        this.row.views = views;
        this.lineRows.add(this.row);
        this.row = new FlowLayoutManager.Row();
    }

    public boolean canScrollVertically() {
        return true;
    }

    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.d("TAG", "totalHeight:" + this.totalHeight);
        int travel = dy;
        if(this.verticalScrollOffset + dy < 0) {
            travel = -this.verticalScrollOffset;
        } else if(this.verticalScrollOffset + dy > this.totalHeight - this.getVerticalSpace()) {
            travel = this.totalHeight - this.getVerticalSpace() - this.verticalScrollOffset;
        }

        this.verticalScrollOffset += travel;
        this.offsetChildrenVertical(-travel);
        this.fillLayout(recycler, state);
        return travel;
    }

    private int getVerticalSpace() {
        return this.self.getHeight() - this.self.getPaddingBottom() - this.self.getPaddingTop();
    }

    public int getHorizontalSpace() {
        return this.self.getWidth() - this.self.getPaddingLeft() - this.self.getPaddingRight();
    }

    public class Row {
        float cuTop;
        float maxHeight;
        List<Item> views = new ArrayList();

        public Row() {
        }

        public void setCuTop(float cuTop) {
            this.cuTop = cuTop;
        }

        public void setMaxHeight(float maxHeight) {
            this.maxHeight = maxHeight;
        }

        public void addViews(FlowLayoutManager.Item view) {
            this.views.add(view);
        }
    }

    public class Item {
        int useHeight;
        View view;
        Rect rect;

        public void setRect(Rect rect) {
            this.rect = rect;
        }

        public Item(int useHeight, View view, Rect rect) {
            this.useHeight = useHeight;
            this.view = view;
            this.rect = rect;
        }
    }
}
