package com.example.smartmuseum.view.goods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityGoodsInfoDetailBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;
import com.example.smartmuseum.viewmodel.GoodsViewModel;

public class GoodsInfoDetailActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityGoodsInfoDetailBinding mBinding;

    private GoodsViewModel goodsDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public GoodsInfoDetailActivity bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_goods_info_detail);
//        goodsDetailViewModel.getClassicalGoodsModelList(map).observe(getViewLifecycleOwner(), models -> {
//            goodsClassicalList = models;
//            mBinding.goodsMarketClassicalRecyclerview.setLayoutManager(new LinearLayoutManager(mBinding.getRoot().getContext()););
//            mBinding.goodsMarketClassicalRecyclerview.setAdapter(new GoodsMarketClassicalAdapter(goodsClassicalList));
//        });
        return this;
    }

    @Override
    public GoodsInfoDetailActivity bindView() {
        ScreenUtil.fullScreen(GoodsInfoDetailActivity.this);
        return this;
    }

    @Override
    public GoodsInfoDetailActivity bindEvent() {
        return this;
    }
}
