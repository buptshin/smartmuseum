package com.example.smartmuseum.view.goods;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.MainPageGoodsCommendAdapter;
import com.example.smartmuseum.adapter.MainPageGoodsPurchasedAdapter;
import com.example.smartmuseum.databinding.FragmentGoodsPurchasedBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.Goods;
import com.example.smartmuseum.viewmodel.GoodsViewModel;

import java.util.HashMap;
import java.util.List;

public class GoodsPurchasedFragment extends Fragment implements ViewChainedBinding {

    private FragmentGoodsPurchasedBinding mBinding;
    private GoodsViewModel goodsViewModel;
    private List<Goods> goodsList;

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
        goodsViewModel = new ViewModelProvider(this).get(GoodsViewModel.class);
        HashMap<String, String> map = new HashMap<>();
        //Activity调用this, fragment调用getViewLifecycleOwner()
        goodsViewModel.getPurchasedGoodsModelList(map).observe(getViewLifecycleOwner(), models -> {
            goodsList = models;
            mBinding.goodsPurchasedRecyclerview.setLayoutManager(new LinearLayoutManager(mBinding.getRoot().getContext()));
            mBinding.goodsPurchasedRecyclerview.setAdapter(new MainPageGoodsPurchasedAdapter(goodsList));
        });
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