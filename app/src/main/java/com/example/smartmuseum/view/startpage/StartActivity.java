package com.example.smartmuseum.view.startpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.StartPageAdapter;
import com.example.smartmuseum.databinding.ActivityStartBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;
import com.example.smartmuseum.view.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityStartBinding mBinding;
    private List<Fragment> fragments;
    private int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public StartActivity bindView() {
        // 设置全屏
        ScreenUtil.fullScreen(StartActivity.this);
        mBinding.startpageViewpager.setAdapter(new StartPageAdapter(getSupportFragmentManager(),fragments));
        return this;
    }

    @Override
    public StartActivity bindData() {
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_start);

        fragments = new ArrayList<>();
        fragments.add(StartPageOneFragment.getInstance());
        fragments.add(StartPageTwoFragment.getInstance());
        fragments.add(StartPageThreeFragment.getInstance());

        currentItem = 0;
        return this;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public StartActivity bindEvent() {
        mBinding.startpageViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBinding.startpageViewpager.setOnTouchListener(new View.OnTouchListener() {
            double downX;
            boolean doIntent = false;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(currentItem == 2){
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            downX = event.getX();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            doIntent = event.getX()-downX < 0;
                            break;
                        case MotionEvent.ACTION_UP:
                            if(doIntent){
                                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                                startActivity(intent);
                                currentItem = 3;  // 防止多次进入登录界面
                                finish();
                            }
                            doIntent = false;
                            break;
                    }
                }
                return false;
            }
        });
        return this;
    }

}
