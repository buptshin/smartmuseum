package com.example.smartmuseum.view.mainpage;

import android.content.Intent;
import android.icu.util.Freezable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.MyInfoFragmentPagerAdapter;
import com.example.smartmuseum.databinding.FragmentMainpageMyinfoBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.view.me.FieldGuideActivity;
import com.example.smartmuseum.view.me.MeCatalogueFragment;
import com.example.smartmuseum.view.me.MuseumInfoActivity;
import com.example.smartmuseum.view.me.friend.FriendChooseFragment;
import com.example.smartmuseum.view.me.friend.FriendIndexFragment;
import com.example.smartmuseum.view.otherview.NoScrollViewPager;
import com.example.smartmuseum.view.me.settings.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainPageMyInfoFragment extends Fragment implements ViewChainedBinding {

    private FragmentMainpageMyinfoBinding mBinding;
    private List<Fragment> fragments;


    public static MainPageMyInfoFragment getInstance() {
        MainPageMyInfoFragment fragment = new MainPageMyInfoFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_mainpage_myinfo,
                container,
                false);
        View v = mBinding.getRoot();

        this.bindData().bindView().bindEvent();
        return v;
    }

    @Override
    public MainPageMyInfoFragment bindData() {
        MeCatalogueFragment meCatalogueFragment = MeCatalogueFragment.getInstance();
        FriendChooseFragment friendChooseFragment = FriendChooseFragment.getInstance();
        FriendIndexFragment friendIndexFragment = FriendIndexFragment.getInstance();

        fragments = new ArrayList<>();
        fragments.add(meCatalogueFragment);
        fragments.add(friendIndexFragment);
        fragments.add(friendChooseFragment);

        mBinding.mainpageMyinfoSv.setAdapter(new MyInfoFragmentPagerAdapter(getChildFragmentManager(),fragments));

        return this;
    }

    @Override
    public MainPageMyInfoFragment bindView() {
        mBinding.mainpageMyinfoSv.setCurrentItem(0);  // 默认放置首页
        return this;
    }

    @Override
    public MainPageMyInfoFragment bindEvent() {
        return this;
    }
}
