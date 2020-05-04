package com.example.smartmuseum.view.mainpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentMainpageExploreBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;

public class MainPageExploreFragment extends Fragment implements ViewChainedBinding {

    private FragmentMainpageExploreBinding mBinding;


    public static MainPageExploreFragment getInstance() {
        MainPageExploreFragment fragment = new MainPageExploreFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_mainpage_explore,
                container,
                false);
        View v = mBinding.getRoot();

        this.bindData().bindView().bindEvent();
        return v;
    }

    @Override
    public MainPageExploreFragment bindData() {
        return this;
    }

    @Override
    public MainPageExploreFragment bindView() {
        return this;
    }

    @Override
    public MainPageExploreFragment bindEvent() {
        return null;
    }
}
