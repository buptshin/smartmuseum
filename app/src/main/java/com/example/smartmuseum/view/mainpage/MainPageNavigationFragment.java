package com.example.smartmuseum.view.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.NavigationFragmentPagerAdapter;
import com.example.smartmuseum.databinding.FragmentMainpageNavigationBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.NavigationFlagModel;
import com.example.smartmuseum.view.navigation.NavigationSearchActivity;
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
        /*
        添加fragment
         */
        mBinding.mainpageNavigationSv.setAdapter(new NavigationFragmentPagerAdapter(getChildFragmentManager(),fragments));
        return this;
    }

    @Override
    public MainPageNavigationFragment bindView() {
        return this;
    }

    @Override
    public MainPageNavigationFragment bindEvent() {

        // 进入急救路线地图
        mBinding.mainpageNavigationEscapeRoutesIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
