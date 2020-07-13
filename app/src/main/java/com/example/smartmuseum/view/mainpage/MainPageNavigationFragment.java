package com.example.smartmuseum.view.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.NavigationFragmentPagerAdapter;
import com.example.smartmuseum.databinding.FragmentMainpageNavigationBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.NavigationFlagModel;
import com.example.smartmuseum.view.navigation.NavigationFirstAidFragment;
import com.example.smartmuseum.view.navigation.NavigationFloorsFragment;
import com.example.smartmuseum.view.navigation.NavigationNowFloorFragment;
import com.example.smartmuseum.view.navigation.NavigationSearchActivity;
import com.example.smartmuseum.view.navigation.exhibition.ExhibitionInnerCollectionFragment;
import com.example.smartmuseum.view.otherview.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/*导览fragment*/
public class MainPageNavigationFragment extends Fragment implements ViewChainedBinding {

    private FragmentMainpageNavigationBinding mBinding;
    private List<Fragment> fragments;

    public MainPageNavigationFragment() {
    }


    public static MainPageNavigationFragment getInstance() {
        MainPageNavigationFragment fragment = new MainPageNavigationFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_mainpage_navigation,
                container,
        false);
        View v = mBinding.getRoot();

        this.bindData().bindView().bindEvent();
        return v;
    }

    @Override
    public MainPageNavigationFragment bindData() {
        fragments = new ArrayList<>();

        NavigationFloorsFragment navigationFloorsFragment = NavigationFloorsFragment.getInstance();
        NavigationNowFloorFragment navigationNowFloorFragment = NavigationNowFloorFragment.getInstance();
        ExhibitionInnerCollectionFragment exhibitionInnerCollectionFragment = ExhibitionInnerCollectionFragment.getInstance();
        NavigationFirstAidFragment navigationFirstAidFragment = NavigationFirstAidFragment.getInstance();

        fragments.add(navigationFloorsFragment);
        fragments.add(navigationNowFloorFragment);
        fragments.add(exhibitionInnerCollectionFragment);
        fragments.add(navigationFirstAidFragment);
        mBinding.mainpageNavigationSv.setAdapter(new NavigationFragmentPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,fragments));
        mBinding.mainpageNavigationSv.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3) {
                    // 急救逃生的设置
                    mBinding.mainpageNavigationEscapeRoutesIv.setVisibility(View.GONE);
                    mBinding.mainpageNavigationNowLocationIv.setVisibility(View.GONE);

                    // 背景的设置
                    mBinding.getRoot().setBackgroundColor(getResources().getColor(R.color.mainpage_navigation_red));

                    // “导览”的设置
                    mBinding.mainpageNavigationTv.setTextColor(getResources().getColor(R.color.mainpage_navigation_white));
                    mBinding.mainpageNavigationSearchIv.setImageResource(R.mipmap.mainpage_navigation_search_white);
                }else {
                    // 急救逃生的设置
                    mBinding.mainpageNavigationEscapeRoutesIv.setVisibility(View.VISIBLE);
                    mBinding.mainpageNavigationNowLocationIv.setVisibility(View.VISIBLE);

                    // 背景的设置
                    mBinding.getRoot().setBackgroundColor(getResources().getColor(R.color.mainpage_navigation_white));

                    // “导览”的设置
                    mBinding.mainpageNavigationTv.setTextColor(getResources().getColor(R.color.mainpage_navigation_selectoritem_wordcolor_black));
                    mBinding.mainpageNavigationSearchIv.setImageResource(R.mipmap.mainpage_navigation_search);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return this;
    }

    @Override
    public MainPageNavigationFragment bindView() {
        mBinding.mainpageNavigationSv.setCurrentItem(0);
        return this;
    }

    @Override
    public MainPageNavigationFragment bindEvent() {

        // 进入急救路线地图
        mBinding.mainpageNavigationEscapeRoutesIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.mainpageNavigationSv.setCurrentItem(3,false);
            }
        });
        // 进入搜索界面
        mBinding.mainpageNavigationSearchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),NavigationSearchActivity.class);
                startActivity(intent);
            }
        });
        return this;
    }

}
