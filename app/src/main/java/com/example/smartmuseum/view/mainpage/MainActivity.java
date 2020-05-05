package com.example.smartmuseum.view.mainpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.MainPageFragmentPagerAdapter;
import com.example.smartmuseum.databinding.ActivityMainBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityMainBinding mBinding;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public MainActivity bindData() {
        return this;
    }

    @Override
    public MainActivity bindView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //状态栏字体设为黑色
        ScreenUtil.setAndroidNativeLightStatusBar(MainActivity.this, true);

        //设置fragments
        Fragment mainPageExploreFragment = MainPageExploreFragment.getInstance();
        Fragment mainPageNavigationFragment = MainPageNavigationFragment.getInstance();
        Fragment mainPageGoodsFragment = MainPageGoodsFragment.getInstance();
        Fragment mainPageMyInfoFragment = MainPageMyInfoFragment.getInstance();

        //添加fragments到adapter
        fragments = new ArrayList<>();
        fragments.add(mainPageExploreFragment);
        fragments.add(mainPageNavigationFragment);
        fragments.add(mainPageGoodsFragment);
        fragments.add(mainPageMyInfoFragment);


        //设置adapter
        mBinding.mainpageNoscrollviewpager.setAdapter(new MainPageFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        mBinding.mainpageNoscrollviewpager.setOffscreenPageLimit(3);

        return this;
    }


    @Override
    public MainActivity bindEvent() {
        //设置底部导航栏点击事件
        mBinding.mainpageBottomnavigationview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = -1;
                switch (item.getItemId()) {
                    case R.id.mainpage_navigation_explore_item:
                        position = 0;
                        break;
                    case R.id.mainpage_navigation_navigation_item:
                        position = 1;
                        break;
                    case R.id.mainpage_navigation_goods_item:
                        position = 2;
                        break;
                    case R.id.mainpage_navigation_myinfo_item:
                        position = 3;
                        break;
                    default:
                        return false;
                }
                if (previousPosition != position) {
                    mBinding.mainpageNoscrollviewpager.setCurrentItem(position, false);
                    previousPosition = position;
                }
                return true;
            }
        });
        return this;
    }
}
