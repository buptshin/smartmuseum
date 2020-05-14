package com.example.smartmuseum.view.goods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityGoodsInfoBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.Goods;
import com.example.smartmuseum.util.ScreenUtil;
import com.example.smartmuseum.view.mainpage.MainActivity;
import com.example.smartmuseum.viewmodel.GoodsViewModel;

import java.util.HashMap;

public class GoodsInfoActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityGoodsInfoBinding mBinding;
    private GoodsViewModel goodsViewModel;
    private Goods goods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public GoodsInfoActivity bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_goods_info);
        goodsViewModel = new ViewModelProvider(this).get(GoodsViewModel.class);
        HashMap<String, String> map = new HashMap<>();
        //Activity调用this, fragment调用getViewLifecycleOwner()
        goodsViewModel.getGoodsModelInfo(map).observe(this, models -> {
            goods = models;
            mBinding.setGoods(goods);
        });
        return this;
    }

    @Override
    public GoodsInfoActivity bindView() {
        ScreenUtil.fullScreen(GoodsInfoActivity.this);
        return this;
    }

    @Override
    public GoodsInfoActivity bindEvent() {
        mBinding.goodsInfoReturnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mBinding.goodsInfoAddCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoodsInfoActivity.this, GoodsInfoDetailActivity.class);
                startActivity(intent);
            }
        });
        return this;
    }
}
