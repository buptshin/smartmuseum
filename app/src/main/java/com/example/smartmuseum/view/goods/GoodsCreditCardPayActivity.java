package com.example.smartmuseum.view.goods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityGoodsCreditCardPayBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;

public class GoodsCreditCardPayActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityGoodsCreditCardPayBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public GoodsCreditCardPayActivity bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_goods_credit_card_pay);
        return this;
    }

    @Override
    public GoodsCreditCardPayActivity bindView() {
        //状态栏字体设为黑色
        ScreenUtil.setAndroidNativeLightStatusBar(GoodsCreditCardPayActivity.this, true);
        return this;
    }

    @Override
    public GoodsCreditCardPayActivity bindEvent() {
        mBinding.goodsCreditCardPayReturnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mBinding.goodsCreditCardPayNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoodsCreditCardPayActivity.this, GoodsInfoActivity.class);
                startActivity(intent);
            }
        });
        return this;
    }

}
