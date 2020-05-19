package com.example.smartmuseum.view.mainpage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.ExploreFragmentAdapter;
import com.example.smartmuseum.adapter.MyRecyclerViewAdapter;
import com.example.smartmuseum.databinding.FragmentMainpageExploreBinding;
import com.example.smartmuseum.databinding.FragmentMainpageExploreNewBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.Exhibition;
import com.example.smartmuseum.view.GlobalVariables;
import com.example.smartmuseum.view.explore.ExhibitionContentActivity;
import com.example.smartmuseum.view.explore.ExploreActivityFragment;
import com.example.smartmuseum.view.explore.ExploreBookVisitFragment;
import com.example.smartmuseum.view.explore.ExploreExhibitionFragment;
import com.example.smartmuseum.view.explore.ExploreRecommendRoute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MainPageExploreFragment extends Fragment implements ViewChainedBinding, View.OnClickListener {

    private FragmentMainpageExploreNewBinding mainpageExploreNewBinding;

    private List<Fragment> fragment_list;
    private boolean isApart = false;   // 标识伙伴图标的切换状态



    public static MainPageExploreFragment getInstance() {
        MainPageExploreFragment fragment = new MainPageExploreFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainpageExploreNewBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_mainpage_explore_new,
                container,
                false);
        View v = mainpageExploreNewBinding.getRoot();
        fragment_list = new ArrayList<>();
        this.bindData().bindView().bindEvent();
        return v;
    }

    @Override
    public MainPageExploreFragment bindData() {

        return this;
    }

    @Override
    public MainPageExploreFragment bindView() {

        // 如果有同行伙伴的话，伙伴图标是可见的
        if(GlobalVariables.hasAcompany)
            mainpageExploreNewBinding.mainpageExploreFriendsStatusImg.setVisibility(View.VISIBLE);

        // 设置点击监听
        mainpageExploreNewBinding.activity.setOnClickListener(this);
        mainpageExploreNewBinding.exhibition.setOnClickListener(this);
        mainpageExploreNewBinding.recommendedroute.setOnClickListener(this);
        mainpageExploreNewBinding.bookvisit.setOnClickListener(this);

        // 将子fragment添加至fragment列表
        fragment_list.add(ExploreExhibitionFragment.getInstance());
        fragment_list.add(ExploreActivityFragment.getInstance());
        fragment_list.add(ExploreRecommendRoute.getInstance());
        fragment_list.add(ExploreBookVisitFragment.getInstance());

        // 设置viewpager相关
        mainpageExploreNewBinding.prohibitescrollviewpager.setAdapter(new ExploreFragmentAdapter(getChildFragmentManager(), fragment_list));
        mainpageExploreNewBinding.prohibitescrollviewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mainpageExploreNewBinding.prohibitescrollviewpager.setCurrentItem(0);
        return this;
    }

    @Override
    public MainPageExploreFragment bindEvent() {

        // 单击伙伴图标
        mainpageExploreNewBinding.mainpageExploreFriendsStatusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isApart = !isApart;
                if(isApart)
                    mainpageExploreNewBinding.mainpageExploreFriendsStatusImg.setImageResource(R.mipmap.mainpage_explore_friends_apart);
                else mainpageExploreNewBinding.mainpageExploreFriendsStatusImg.setImageResource(R.mipmap.mainpage_explore_friends_together);
            }
        });

        // 长按伙伴图标，出现侧滑栏
        mainpageExploreNewBinding.mainpageExploreFriendsStatusImg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // 如果当前是伙伴集中的状态，就打开侧滑栏
                if(!isApart){
                    DrawerLayout drawerLayout = (DrawerLayout)mainpageExploreNewBinding.getRoot().getRootView().findViewById(R.id.mainpage_drawer);
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                return false;
            }
        });
        return this;
    }


    @Override
    public void onClick(View view) {
        initImageButton();
        switch (view.getId()) {
            case R.id.exhibition:
                mainpageExploreNewBinding.exhibition.setImageResource(R.mipmap.mainpage_exhibition_selected);
                mainpageExploreNewBinding.exhibitionText.setTextColor(Color.parseColor("#842d29"));
//                Log.d(TAG, "onClick: button1");
                mainpageExploreNewBinding.prohibitescrollviewpager.setCurrentItem(0);
                break;
            case R.id.activity:
                mainpageExploreNewBinding.activity.setImageResource(R.mipmap.activity_selected);
                mainpageExploreNewBinding.activityText.setTextColor(Color.parseColor("#842d29"));
//                Log.d(TAG, "onClick: button2");
                mainpageExploreNewBinding.prohibitescrollviewpager.setCurrentItem(1);
                break;
            case R.id.recommendedroute:
                mainpageExploreNewBinding.recommendedroute.setImageResource(R.mipmap.recommendedroute_selected);
                mainpageExploreNewBinding.recomendedrouteText.setTextColor(Color.parseColor("#842d29"));
//                Log.d(TAG, "onClick: button3");
                mainpageExploreNewBinding.prohibitescrollviewpager.setCurrentItem(2);
                break;
            case R.id.bookvisit:
                mainpageExploreNewBinding.bookvisit.setImageResource(R.mipmap.bookvisit_selected);
                mainpageExploreNewBinding.bookvisitText.setTextColor(Color.parseColor("#842d29"));
//                Log.d(TAG, "onClick: button4");
                mainpageExploreNewBinding.prohibitescrollviewpager.setCurrentItem(3);
                break;
        }
    }

    // 每次点击后还原Button样式
    public void initImageButton() {
        mainpageExploreNewBinding.exhibitionText.setTextColor(Color.parseColor("#222222"));
        mainpageExploreNewBinding.activityText.setTextColor(Color.parseColor("#222222"));
        mainpageExploreNewBinding.recomendedrouteText.setTextColor(Color.parseColor("#222222"));
        mainpageExploreNewBinding.bookvisitText.setTextColor(Color.parseColor("#222222"));

        mainpageExploreNewBinding.exhibition.setImageResource(R.mipmap.exhibition_not_selected);
        mainpageExploreNewBinding.activity.setImageResource(R.mipmap.mainpage_activity_not_selected);
        mainpageExploreNewBinding.recommendedroute.setImageResource(R.mipmap.mainpage_recommendedroute_not_selected);
        mainpageExploreNewBinding.bookvisit.setImageResource(R.mipmap.mainpage_bookvisit_not_selected);

    }
}
