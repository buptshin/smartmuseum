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
import com.example.smartmuseum.databinding.FragmentGoodsMarketBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;

public class GoodsMarketFragment extends Fragment implements ViewChainedBinding {

    private FragmentGoodsMarketBinding mBinding;

    public static GoodsMarketFragment getInstance() {
        GoodsMarketFragment fragment = new GoodsMarketFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_goods_market,
                container,
                false);
        View v = mBinding.getRoot();

        this.bindData().bindView().bindEvent();
        return v;
    }

    @Override
    public GoodsMarketFragment bindData() {
        return this;
    }

    @Override
    public GoodsMarketFragment bindView() {
        return this;
    }

    @Override
    public GoodsMarketFragment bindEvent() {
        return null;
    }
}