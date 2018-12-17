package com.superc.mine.ui.activity;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.superc.mine.R;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "TestActivity";
    private TextView mtv_hour;
    private TextView mtv_minute;
    private TextView mtv_second;
    private TextView mtv_time;
    private Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initEver();

    }

    public void initEver() {
        mtv_hour=findViewById(R.id.test_hour);
        mtv_minute=findViewById(R.id.test_minute);
        mtv_second=findViewById(R.id.test_second);
        mtv_time=findViewById(R.id.test_time);
        mBt=findViewById(R.id.button);
        mBt.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                handler.sendEmptyMessage(0);
                break;


        }
    }
    //使用handler用于更新UI
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            countDown();
            sendEmptyMessageDelayed(0, 1000);
        }
    };
    /**
     * 秒杀
     */
    private void countDown() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String format = df.format(curDate);
        Log.e(TAG, "countDown: format="+format);
        StringBuffer buffer = new StringBuffer();
        String substring = format.substring(0, 11);
        buffer.append(substring);
        Log.e(TAG,"substring="+ substring);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour % 2 == 0) {
            mtv_time.setText(hour + "点场");
            buffer.append((hour + 2));
            buffer.append(":00:00");
        } else {
            mtv_time.setText((hour - 1) + "点场");
            buffer.append((hour + 1));
            buffer.append(":00:00");
        }
        String totime = buffer.toString();
        try {
            java.util.Date date = df.parse(totime);
            java.util.Date date1 = df.parse(format);
            long defferenttime = date.getTime() - date1.getTime();
            long days = defferenttime / (1000 * 60 * 60 * 24);
            long hours = (defferenttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minute = (defferenttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long seconds = defferenttime % 60000;
            long second = Math.round((float) seconds / 1000);
            mtv_hour.setText("0" + hours + "");
            if (minute >= 10) {
                mtv_minute.setText(minute + "");
            } else {
                mtv_minute.setText("0" + minute + "");
            }
            if (second >= 10) {
                mtv_second.setText(second + "");
            } else {
                mtv_second.setText("0" + second + "");
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
