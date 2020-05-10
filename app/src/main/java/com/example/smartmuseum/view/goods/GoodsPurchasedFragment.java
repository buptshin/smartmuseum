package com.example.smartmuseum.view.goods;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentGoodsPurchasedBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;

public class GoodsPurchasedFragment extends Fragment implements ViewChainedBinding {

    private FragmentGoodsPurchasedBinding mBinding;

    public static GoodsPurchasedFragment getInstance() {
        GoodsPurchasedFragment fragment = new GoodsPurchasedFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_goods_purchased,
                container,
                false);
        View v = mBinding.getRoot();

        this.bindData().bindView().bindEvent();
        return v;
    }

    @Override
    public GoodsPurchasedFragment bindData() {
        return this;
    }

    @Override
    public GoodsPurchasedFragment bindView() {
        return this;
    }

    @Override
    public GoodsPurchasedFragment bindEvent() {
        return null;
    }
}