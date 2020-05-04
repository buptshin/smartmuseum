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
import com.example.smartmuseum.databinding.FragmentMainpageGoodsBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;

public class MainPageGoodsFragment extends Fragment implements ViewChainedBinding {

    private FragmentMainpageGoodsBinding mBinding;


    public static MainPageGoodsFragment getInstance() {
        MainPageGoodsFragment fragment = new MainPageGoodsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_mainpage_goods,
                container,
                false);
        View v = mBinding.getRoot();

        this.bindData().bindView().bindEvent();
        return v;
    }

    @Override
    public MainPageGoodsFragment bindData() {
        return this;
    }

    @Override
    public MainPageGoodsFragment bindView() {
        return this;
    }

    @Override
    public MainPageGoodsFragment bindEvent() {
        return null;
    }
}