package com.example.smartmuseum.view.goods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityGoodsOrderStatusBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;

public class GoodsOrderStatusActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityGoodsOrderStatusBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public GoodsOrderStatusActivity bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_goods_order_status);
        return this;
    }

    @Override
    public GoodsOrderStatusActivity bindView() {
        //状态栏字体设为黑色
        ScreenUtil.setAndroidNativeLightStatusBar(GoodsOrderStatusActivity.this, true);
        return this;
    }

    @Override
    public GoodsOrderStatusActivity bindEvent() {
        mBinding.goodsOrderStatusInfoMapImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoodsOrderStatusActivity.this, GoodsOrderDeliveryActivity.class);
                startActivity(intent);
            }
        });
        mBinding.goodsOrderStatusReturnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        return this;
    }
}
