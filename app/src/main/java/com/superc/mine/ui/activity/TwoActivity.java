package com.superc.mine.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.superc.mine.R;
import com.superc.mine.bean.Message;
import com.superc.yf_lib.base.BaseActivity;
import com.superc.yf_lib.net.Http;

import java.util.HashMap;

public class TwoActivity extends BaseActivity {
    private static final String TAG = "TwoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
    }

    @Override
    public void init() {
        configeSimpleTitle("第二页", true);
    }

    public void goMySelf(View v) {
        Http.getInstance(this,false).ConnectUrl(new HashMap<String, String>(), "", Message.class, new Http.CallNetBack<Message>() {
            @Override
            public void callNetBack(Message data) {

            }

            @Override
            public void callFailBack(String data) {

            }
        });

    }

    @Override
    public void onClick(View view) {

    }

}
