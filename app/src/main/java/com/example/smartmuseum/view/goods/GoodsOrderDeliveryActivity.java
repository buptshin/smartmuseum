package com.example.smartmuseum.view.goods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityGoodsOrderDeliveryBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;

public class GoodsOrderDeliveryActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityGoodsOrderDeliveryBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public GoodsOrderDeliveryActivity bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_goods_order_delivery);
        return this;
    }

    @Override
    public GoodsOrderDeliveryActivity bindView() {
        //状态栏字体设为黑色
        ScreenUtil.setAndroidNativeLightStatusBar(GoodsOrderDeliveryActivity.this, true);
        mBinding.goodsOrderDeliveryTimeView.setScales(new double[]{0.8, 0.2});
        return this;
    }

    @Override
    public GoodsOrderDeliveryActivity bindEvent() {
        mBinding.goodsOrderDeliveryReturnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        return this;
    }
}
