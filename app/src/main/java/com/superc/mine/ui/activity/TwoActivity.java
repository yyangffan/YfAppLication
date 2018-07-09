package com.superc.mine.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.superc.mine.R;
import com.superc.yf_lib.base.BaseActivity;

public class TwoActivity extends BaseActivity {
    private static final String TAG = "TwoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
    }

    @Override
    public void init() {
        simpleConfigeTitle("第二页", true);
    }

    public void goMySelf(View v) {
    }

    @Override
    public void onClick(View view) {

    }

}
