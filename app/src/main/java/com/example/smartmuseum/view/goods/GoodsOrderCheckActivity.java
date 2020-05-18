package com.example.smartmuseum.view.goods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.GoodsOrderCheckAdapter;
import com.example.smartmuseum.databinding.ActivityGoodsOrderCheckBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.Goods;
import com.example.smartmuseum.util.ScreenUtil;
import com.example.smartmuseum.viewmodel.GoodsViewModel;

import java.util.HashMap;
import java.util.List;

public class GoodsOrderCheckActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityGoodsOrderCheckBinding mBinding;
    private List<Goods> goodsList;
    private GoodsViewModel goodsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public GoodsOrderCheckActivity bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_goods_order_check);
        goodsViewModel = new ViewModelProvider(this).get(GoodsViewModel.class);
        HashMap<String, String> map = new HashMap<>();
        goodsViewModel.getbuyGoodsModelList(map).observe(this, models -> {
            goodsList = models;

            int goodsPrices = 0;
            for (Goods goods : goodsList) {
                goodsPrices += goods.getPrice();
            }

            int goodsPackPrice = 5;
            int goodsDeliverPrice = 5;

            int goodsSumPrice = goodsPrices + goodsPackPrice + goodsDeliverPrice;

            mBinding.goodsOrderCheckGoodsPriceText.setText(getResources().getString(R.string.mainpage_goods_price_sign) + goodsPrices);
            mBinding.goodsOrderCheckPackPriceText.setText(getResources().getString(R.string.mainpage_goods_price_sign) + goodsPackPrice);
            mBinding.goodsOrderCheckDeliverPriceText.setText(getResources().getString(R.string.mainpage_goods_price_sign) + goodsDeliverPrice);
            mBinding.goodsOrderCheckSumPriceText.setText(getResources().getString(R.string.mainpage_goods_price_sign) + goodsSumPrice);

            mBinding.goodsOrderCheckRecyclerview.setLayoutManager(new LinearLayoutManager(mBinding.getRoot().getContext()));
            mBinding.goodsOrderCheckRecyclerview.setAdapter(new GoodsOrderCheckAdapter(goodsList));
        });
        return this;
    }

    @Override
    public GoodsOrderCheckActivity bindView() {
        //状态栏字体设为黑色
        ScreenUtil.setAndroidNativeLightStatusBar(GoodsOrderCheckActivity.this, true);
        return this;
    }

    @Override
    public GoodsOrderCheckActivity bindEvent() {
        mBinding.goodsInfoDetailReturnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mBinding.goodsOrderCheckPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoodsOrderCheckActivity.this, GoodsChooseAddressActivity.class);
                startActivity(intent);
            }
        });
        return this;
    }
}
