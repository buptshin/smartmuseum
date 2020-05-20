package com.example.smartmuseum.view.mainpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.MainPageFragmentPagerAdapter;
import com.example.smartmuseum.databinding.ActivityMainBinding;
import com.example.smartmuseum.databinding.FragmentForgetPwdResetBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;
import com.example.smartmuseum.view.GlobalVariables;
import com.example.smartmuseum.view.exhibition.ExhibitionInnerCollectionFragment;
import com.example.smartmuseum.view.explore.ExploreActivityFragment;
import com.example.smartmuseum.view.explore.ExploreBookVisitFragment;
import com.example.smartmuseum.view.explore.ExploreRecommendRoute;
import com.example.smartmuseum.view.friend.ChooseFriendsFragment;
import com.example.smartmuseum.view.friend.MyFriendsFragment;
import com.example.smartmuseum.view.goods.GoodsRecommendActivity;
import com.example.smartmuseum.view.goods.GoodsRecommendActivity;
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
        mBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        //状态栏字体设为黑色
        ScreenUtil.fullScreen(MainActivity.this);
        ScreenUtil.setAndroidNativeLightStatusBar(MainActivity.this, true);

        //禁止侧滑栏的手动滑动
        mBinding.mainpageDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        //设置fragments
        //主页的四个fragment
        Fragment mainPageExploreFragment = MainPageExploreFragment.getInstance();
        Fragment mainPageNavigationFragment = MainPageNavigationFragment.getInstance();
        Fragment mainPageGoodsFragment = MainPageGoodsFragment.getInstance();
        Fragment mainPageMyInfoFragment = MainPageMyInfoFragment.getInstance();
        //个人界面的两个fragment
        Fragment myFriendsFragment = MyFriendsFragment.getInstance();
        Fragment chooseFriendsFragment = ChooseFriendsFragment.getInstance();
        //展厅地图的fragment
        Fragment exhibitionInnerCollectionFragment = ExhibitionInnerCollectionFragment.getInstance();

        //添加fragments到adapter
        fragments = new ArrayList<>();
        fragments.add(mainPageExploreFragment);
        fragments.add(mainPageNavigationFragment);
        fragments.add(mainPageGoodsFragment);
        fragments.add(mainPageMyInfoFragment);
        fragments.add(myFriendsFragment);
        fragments.add(chooseFriendsFragment);
        fragments.add(exhibitionInnerCollectionFragment);


        //设置adapter
        mBinding.mainpageNoscrollviewpager.setAdapter(new MainPageFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        mBinding.mainpageNoscrollviewpager.setOffscreenPageLimit(3);

        return this;
    }


    @Override
    public MainActivity bindEvent() {
        // activity的跳转事件
        int tag = getIntent().getIntExtra("exhibition",0);
        if(tag == 1){
            mBinding.mainpageNoscrollviewpager.setCurrentItem(6,false);
            mBinding.mainpageBottomnavigationview.setSelectedItemId(R.id.mainpage_navigation_navigation_item);
        }
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
                        Intent intent = new Intent(MainActivity.this, GoodsRecommendActivity.class);
                        startActivity(intent);
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
