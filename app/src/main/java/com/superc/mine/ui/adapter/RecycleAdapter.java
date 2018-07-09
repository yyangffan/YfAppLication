package com.superc.mine.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.superc.mine.R;
import com.superc.mine.bean.Message;

import java.util.List;

/**
 * Created by user on 2018/6/5.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private Context mContext;
    private List<Message> mLists;
    private LayoutInflater mInflater;

    public RecycleAdapter(Context context, List<Message> stringList) {
        mContext = context;
        mLists = stringList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recy, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        Message bean = mLists.get(position);
        vh.mIvIcon.setImageResource(bean.getIcon_id());
        vh.mTvUsername.setText(bean.getName());
        vh.mTvTime.setText(bean.getTime());
        vh.mTvMessage.setText(bean.getContent());

    }

    @Override
    public int getItemCount() {
        return mLists == null ? 0 : mLists.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvIcon;
        TextView mTvUsername;
        TextView mTvTime;
        TextView mTvMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            mIvIcon = itemView.findViewById(R.id.iv_icon);
            mTvUsername = itemView.findViewById(R.id.tv_username);
            mTvTime = itemView.findViewById(R.id.tv_time);
            mTvMessage = itemView.findViewById(R.id.tv_message);

        }
    }
}


