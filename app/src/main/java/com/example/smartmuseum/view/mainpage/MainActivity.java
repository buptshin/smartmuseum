package com.example.smartmuseum.view.mainpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.MainPageFragmentPagerAdapter;
import com.example.smartmuseum.databinding.ActivityMainBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;
import com.example.smartmuseum.view.navigation.exhibition.ExhibitionInnerCollectionFragment;
import com.example.smartmuseum.view.me.friend.FriendChooseFragment;
import com.example.smartmuseum.view.me.friend.FriendIndexFragment;
import com.example.smartmuseum.view.goods.GoodsRecommendActivity;
import com.example.smartmuseum.view.navigation.NavigationFirstAidFragment;
import com.example.smartmuseum.view.navigation.NavigationNowFloorFragment;
import com.example.smartmuseum.viewmodel.AccompanyCountViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityMainBinding mBinding;
    private List<Fragment> fragments;
    private AccompanyCountViewModel accompanyCountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public MainActivity bindData() {
        mBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        accompanyCountViewModel = new ViewModelProvider(MainActivity.this).get(AccompanyCountViewModel.class);
        mBinding.setData(accompanyCountViewModel);
        mBinding.setLifecycleOwner(this);
        return this;
    }

    @Override
    public MainActivity bindView() {

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
        Fragment myFriendsFragment = FriendIndexFragment.getInstance();
        Fragment chooseFriendsFragment = FriendChooseFragment.getInstance();
        //展厅地图的fragment
        Fragment exhibitionInnerCollectionFragment = ExhibitionInnerCollectionFragment.getInstance();
        //急救路线fragment
        Fragment firstAidFragment = NavigationFirstAidFragment.getInstance();
        //当前楼层导览fragment
        Fragment nowFloorFragment = NavigationNowFloorFragment.getInstance();

        //添加fragments到adapter
        fragments = new ArrayList<>();
        fragments.add(mainPageExploreFragment);
        fragments.add(mainPageNavigationFragment);
        fragments.add(mainPageGoodsFragment);
        fragments.add(mainPageMyInfoFragment);
        fragments.add(myFriendsFragment);
        fragments.add(chooseFriendsFragment);
        fragments.add(exhibitionInnerCollectionFragment);
        fragments.add(firstAidFragment);
        fragments.add(nowFloorFragment);


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
        mBinding.mainpageDrawerExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.mainpageDrawer.closeDrawer(GravityCompat.START);
            }
        });
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

                mBinding.mainpageNoscrollviewpager.setCurrentItem(position, false);
                previousPosition = position;
                //因为导览里面有多个fragment切换的情况
                if (position == 1){
                    mBinding.mainpageNoscrollviewpager.setCurrentItem(position, false);
                    previousPosition = position;
                }else if (previousPosition != position) {
                    mBinding.mainpageNoscrollviewpager.setCurrentItem(position, false);
                    previousPosition = position;
                }
                return true;
            }
        });
        return this;
    }
}
