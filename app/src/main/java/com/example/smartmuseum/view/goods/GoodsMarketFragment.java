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
import com.example.smartmuseum.adapter.GoodsMarketDiscountAdapter;
import com.example.smartmuseum.databinding.FragmentGoodsMarketBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.Goods;
import com.example.smartmuseum.viewmodel.GoodsViewModel;

import java.util.HashMap;
import java.util.List;

public class GoodsMarketFragment extends Fragment implements ViewChainedBinding {

    private FragmentGoodsMarketBinding mBinding;
    private List<Goods> goodsList;
    private GoodsViewModel goodsDiscountViewModel;

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


        goodsDiscountViewModel = new ViewModelProvider(this).get(GoodsViewModel.class);
        HashMap<String, String> map = new HashMap<>();
        //Activity调用this, fragment调用getViewLifecycleOwner()
        goodsDiscountViewModel.getDiscountGoodsModelList(map).observe(getViewLifecycleOwner(), models -> {
            goodsList = models;
            LinearLayoutManager ms = new LinearLayoutManager(mBinding.getRoot().getContext());
            ms.setOrientation(LinearLayoutManager.HORIZONTAL);
            mBinding.goodsMarketDiscountRecyclerview.setLayoutManager(ms);
            mBinding.goodsMarketDiscountRecyclerview.setAdapter(new GoodsMarketDiscountAdapter(goodsList));
        });
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