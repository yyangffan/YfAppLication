package com.superc.mine.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.superc.mine.R;
import com.superc.mine.ui.adapter.FashionRecyAdapter;
import com.superc.yf_lib.views.spec_view.FlowLayoutManager;
import com.superc.yf_lib.views.spec_view.NestedRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/********************************************************************
 @version: 1.0.0
 @description:
 @author: 杨帆
 @time: 2018/8/6 17:04
 @变更历史:
 ********************************************************************/

public class FashionFragment extends Fragment {
    private static final String TAG = "FashionFragment";
    private View mV;
    private NestedRecyclerView mRecyclerView;
    private List<Map<String, Object>> mMapList;
    private FashionRecyAdapter mFashionRecyAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mV = inflater.inflate(R.layout.fragment_fashion, container, false);
        initEver();
        return mV;
    }

    public void initEver() {
        mRecyclerView = mV.findViewById(R.id.fashion_recy);
        mMapList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            String title = "";
            for (int j = 0; j < i+1; j++) {
                title += "规格" + j + ",";
            }
            map.put("title", title.substring(0, title.length() - 1));
            mMapList.add(map);
        }
        mFashionRecyAdapter = new FashionRecyAdapter(this.getActivity(), mMapList);

        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        mRecyclerView.setLayoutManager(flowLayoutManager);
        mRecyclerView.setAdapter(mFashionRecyAdapter);

    }

}
