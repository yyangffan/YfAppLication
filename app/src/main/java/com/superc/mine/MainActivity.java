package com.superc.mine;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.superc.mine.ui.adapter.MainMvpAdapter;
import com.superc.mine.ui.fragment.FashionFragment;
import com.superc.mine.ui.fragment.HomeFragment;
import com.superc.mine.ui.fragment.MineFragment;
import com.superc.yf_lib.base.BaseActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.main_page)
    ViewPager mMainPage;
    @BindView(R.id.main_rb_one)
    RadioButton mMainRbOne;
    @BindView(R.id.main_rb_two)
    RadioButton mMainRbTwo;
    @BindView(R.id.main_rb_three)
    RadioButton mMainRbThree;
    @BindView(R.id.main_rg)
    RadioGroup mMainRg;

    private HomeFragment mHomeFragment;
    private MineFragment mMineFragment;
    private FashionFragment mFashionFragment;
    private MainMvpAdapter mMvpAdapter;

    private List<Fragment> mFragments;
    private static final String TAG = "MainActivity";
    private DecimalFormat mDecimalFormat = new DecimalFormat("0.0");
    private List<RadioButton> mRadioButtons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    public void init() {
        configeSimpleTitle("主页", false);
        mFragments = new ArrayList<>();
        mRadioButtons = new ArrayList<>();
        mHomeFragment = new HomeFragment();
        mMineFragment = new MineFragment();
        mFashionFragment = new FashionFragment();
        mFragments.add(mHomeFragment);
        mFragments.add(mMineFragment);
        mFragments.add(mFashionFragment);

        mRadioButtons.add(mMainRbOne);
        mRadioButtons.add(mMainRbTwo);
        mRadioButtons.add(mMainRbThree);

        mMvpAdapter = new MainMvpAdapter(this.getSupportFragmentManager(), mFragments);
        mMainPage.setAdapter(mMvpAdapter);
        mMainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                switch (id) {
                    case R.id.main_rb_one:
                        mMainPage.setCurrentItem(0);

                        break;
                    case R.id.main_rb_two:
                        mMainPage.setCurrentItem(1);

                        break;
                    case R.id.main_rb_three:
                        mMainPage.setCurrentItem(2);

                        break;
                }
            }
        });

        mMainPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                String format = mDecimalFormat.format(positionOffset * 10);
//                Log.e(TAG, "onPageScrolled: " + "position:" + position + "     需要变化的大小:" + format);
                if(position<mRadioButtons.size()-1){
                    mRadioButtons.get(position).setTextSize(24-Float.parseFloat(format));
                    mRadioButtons.get(position+1).setTextSize(14 + Float.parseFloat(format));
                }


            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0;i<mRadioButtons.size();i++){
                    if(i==position){
                        mRadioButtons.get(i).setTextSize(24);
                    }else{
                        mRadioButtons.get(i).setTextSize(14);
                    }
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    //    @OnClick(R.id.main_tv)
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.main_tv:
//                stActivity(TwoActivity.class);
//                break;
//        }

    }
}
