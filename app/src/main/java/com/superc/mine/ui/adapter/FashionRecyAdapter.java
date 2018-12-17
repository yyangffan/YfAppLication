package com.superc.mine.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.superc.mine.R;

import java.util.List;
import java.util.Map;
/********************************************************************
  @version: 1.0.0
  @description:
  @author: 杨帆
  @time: 2018/8/6 17:26
  @变更历史:
********************************************************************/

public class FashionRecyAdapter extends RecyclerView.Adapter<FashionRecyAdapter.ViewHolder> {
    private Context mContext;
    private List<Map<String, Object>> mLists;
    private LayoutInflater mInflater;

    public FashionRecyAdapter(Context context, List<Map<String, Object>> stringList) {
        mContext = context;
        mLists = stringList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_fashion, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        Map<String, Object> bean = mLists.get(position);
        String title = (String) bean.get("title");
        vh.mtv_title.setText(title);

    }

    @Override
    public int getItemCount() {
        return mLists == null ? 0 : mLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mtv_title;

        public ViewHolder(View itemView) {
            super(itemView);
            mtv_title = itemView.findViewById(R.id.item_fashion_title);


        }
    }
}

