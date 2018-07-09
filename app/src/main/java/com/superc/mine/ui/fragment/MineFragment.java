package com.superc.mine.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.superc.mine.R;
import com.superc.mine.bean.Message;
import com.superc.mine.ui.adapter.RecycleAdapter;
import com.superc.yf_lib.base.BaseFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.mine_recy)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    private RecycleAdapter mRecycleAdapter;
    private List<Message> mMessageList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void init() {
        initRecy();

    }

    @Override
    public void onClick(View view) {

    }

    public void initRecy() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(), 2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mMessageList = new ArrayList<>();
        mMessageList.add(new Message("Hensen", "下午1:22", "老板：哈哈哈", R.drawable.default_portrait));
        mMessageList.add(new Message("流年不利", "早上10:31", "美女：呵呵哒", R.drawable.sum_img));
        mMessageList.add(new Message("1402", "下午1:55", "嘻嘻哈哈", R.drawable.default_portrait));
        mMessageList.add(new Message("Unstable", "下午4:32", "美美哒", R.drawable.sum_img));
        mMessageList.add(new Message("流年不利", "晚上7:22", "萌萌哒", R.drawable.default_portrait));
        mMessageList.add(new Message("Hensen", "下午1:22", "哈哈哈f", R.drawable.sum_img));
        mMessageList.add(new Message("Hensen", "下午1:22", "哈哈哈s", R.drawable.default_portrait));
        mMessageList.add(new Message("Hensen", "下午1:22", "哈哈哈a", R.drawable.sum_img));
        mMessageList.add(new Message("流年不利", "晚上7:22", "萌萌哒", R.drawable.default_portrait));
        mMessageList.add(new Message("Hensen", "下午1:22", "哈哈哈1", R.drawable.sum_img));
        mMessageList.add(new Message("Hensen", "下午1:22", "哈哈哈s3", R.drawable.default_portrait));
        mMessageList.add(new Message("Hensen", "下午1:22", "哈哈哈a41", R.drawable.sum_img));
        mRecycleAdapter = new RecycleAdapter(this.getActivity(), mMessageList);
        mRecyclerView.setAdapter(mRecycleAdapter);

        //为RecycleView绑定触摸事件
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //首先回调的方法 返回int表示是否监听该方向
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;//拖拽
                int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;//侧滑删除
                return makeMovementFlags(dragFlags, swipeFlags);

//                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;//拖拽
//                int swipeFlags = 0;//侧滑删除
//                return makeMovementFlags(dragFlags, swipeFlags);

            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //滑动事件
                Collections.swap(mMessageList, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                mRecycleAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //侧滑事件
                mMessageList.remove(viewHolder.getAdapterPosition());
                mRecycleAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }

            @Override
            public boolean isLongPressDragEnabled() {
                //是否可拖拽
                return true;
            }
        });
        helper.attachToRecyclerView(mRecyclerView);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
