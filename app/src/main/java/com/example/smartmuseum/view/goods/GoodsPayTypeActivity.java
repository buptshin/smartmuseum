package com.example.smartmuseum.view.goods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityGoodsPayTypeBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;

public class GoodsPayTypeActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityGoodsPayTypeBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public GoodsPayTypeActivity bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_goods_pay_type);
        return this;
    }

    @Override
    public GoodsPayTypeActivity bindView() {
        //状态栏字体设为黑色
        ScreenUtil.setAndroidNativeLightStatusBar(GoodsPayTypeActivity.this, true);
        return this;
    }

    @Override
    public GoodsPayTypeActivity bindEvent() {
        mBinding.goodsPayTypeReturnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mBinding.gooddPayTypeCreditcardpayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoodsPayTypeActivity.this, GoodsCreditCardPayActivity.class);
                startActivity(intent);
            }
        });
        return this;
    }
}
