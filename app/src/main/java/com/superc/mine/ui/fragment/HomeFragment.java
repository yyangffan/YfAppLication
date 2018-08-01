package com.superc.mine.ui.fragment;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.superc.mine.R;
import com.superc.mine.ui.activity.TestActivity;
import com.superc.yf_lib.base.BaseFragment;
import com.superc.yf_lib.utils.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.home_search_edt)
    EditText mHomeSearchEdt;
    @BindView(R.id.home_search_imgv)
    ImageView mHomeSearchImgv;
    @BindView(R.id.home_search_ll)
    LinearLayout mHomeSearchLl;
    @BindView(R.id.home_banner)
    Banner mHomeBanner;
    @BindView(R.id.home_tv_test)
    TextView mHomeTvTest;

    private boolean isBig;
    private List<String> mStringList;
    private List<String> titles;

    private static final String TAG = "HomeFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void init() {
        mStringList = new ArrayList<>();
        titles = new ArrayList<>();

        initBanner();
    }


    @OnClick({R.id.home_search_imgv,R.id.home_tv_test})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_search_imgv:
                if (isBig) {
                    small();
                } else {
                    big();
                }
                isBig = !isBig;
                break;
            case R.id.home_tv_test:
                Toast.makeText(this.getActivity(), new DecimalFormat("0.00").format(1.156), Toast.LENGTH_LONG).show();
                stActivity(TestActivity.class);
                break;
        }
    }

    /*初始化轮播图Bannaer https://github.com/youth5201314/banner*/
    public void initBanner() {
        mStringList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529997035&di=cdc8f7cb17e70ff510c83685c0493eaa&imgtype=jpg&er=" +
                "1&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F03087bf40ad162d9a62a929b1ddfa9ec8b13cd75.jpg");
        mStringList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529997212&di=f46b6b10e323b914238123c937fd48e8&imgtype=jpg&er=1&s" +
                "rc=http%3A%2F%2Fa.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fc9fcc3cec3fdfc039afa6e3cd83f8794a5c226b7.jpg");
        mStringList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529997230&di=a2699170613181f59460876ed5cd2906&imgtype=jpg&er=1&sr" +
                "c=http%3A%2F%2Fe.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F0e2442a7d933c8950db7aff3dd1373f083020068.jpg");
        titles.add("你好啊");
        titles.add("哈哈哈");
        titles.add("嘿嘿嘿");
        mHomeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);  //设置banner样式
        mHomeBanner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器位置
        mHomeBanner.setBannerAnimation(Transformer.BackgroundToForeground);//设置动画(有很多可选)不过可能导致问题尽量不用
        mHomeBanner.setImageLoader(new GlideImageLoader());
        mHomeBanner.setImages(mStringList);  //设置图片集合
        mHomeBanner.setBannerTitles(titles);//设置标题集合
        mHomeBanner.start(); //banner设置方法全部调用完毕时最后调用
        mHomeBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtil.showToast(HomeFragment.this.getActivity(), "点到了第" + position + "个");
            }
        });
    }

    /*布局变大*/
    private void big() {
        ViewGroup.LayoutParams layoutParams = mHomeSearchLl.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        mHomeSearchLl.setLayoutParams(layoutParams);
        mHomeSearchEdt.setHint("请输入搜索内容");
        beginDelayedTransition(mHomeSearchLl);
    }

    /*布局变小*/
    private void small() {
        ViewGroup.LayoutParams layoutParams = mHomeSearchLl.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        mHomeSearchLl.setLayoutParams(layoutParams);
        mHomeSearchEdt.setHint("");
        beginDelayedTransition(mHomeSearchLl);
    }

    /*开始动画*/
    private void beginDelayedTransition(ViewGroup view) {
        AutoTransition mSet = new AutoTransition();
        //设置动画持续时间
        mSet.setDuration(300);
        // 开始表演
        TransitionManager.beginDelayedTransition(view, mSet);
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */

            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

            //用fresco加载图片简单用法，记得要写下面的createImageView方法
            Uri uri = Uri.parse((String) path);
            imageView.setImageURI(uri);
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
        @Override
        public ImageView createImageView(Context context) {
            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
            ImageView simpleDraweeView = new ImageView(context);
            return simpleDraweeView;
        }
    }
}
