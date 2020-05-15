package com.example.smartmuseum.view.goods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.GoodsInfoDetailAdapter;
import com.example.smartmuseum.databinding.ActivityGoodsInfoDetailBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.Goods;
import com.example.smartmuseum.util.ScreenUtil;
import com.example.smartmuseum.viewmodel.GoodsViewModel;

import java.util.HashMap;
import java.util.List;

public class GoodsInfoDetailActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityGoodsInfoDetailBinding mBinding;

    private GoodsViewModel goodsDetailViewModel;

    private Goods goods;

    private boolean choooseTypeFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public GoodsInfoDetailActivity bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_goods_info_detail);

        goodsDetailViewModel = new ViewModelProvider(this).get(GoodsViewModel.class);
        HashMap<String, String> map = new HashMap<>();
        goodsDetailViewModel.goodsInfoDetailInfo(map).observe(this, models -> {
            goods = models;
            mBinding.setGoods(goods);
            mBinding.goodsInfoDetailGoodsDetailRecyclerview.setLayoutManager(new LinearLayoutManager(mBinding.getRoot().getContext()));
            mBinding.goodsInfoDetailGoodsDetailRecyclerview.setAdapter(new GoodsInfoDetailAdapter(goods.getFeature()));
        });
        return this;
    }

    @Override
    public GoodsInfoDetailActivity bindView() {
        ScreenUtil.fullScreen(GoodsInfoDetailActivity.this);
        return this;
    }

    @Override
    public GoodsInfoDetailActivity bindEvent() {
        mBinding.goodsInfoDetailGoodsNoboxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //有盒子是true,没盒子是flase
                if (choooseTypeFlag) {
                    choooseTypeFlag = false;
                    mBinding.goodsInfoDetailGoodsNoboxButton.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.goods_info_detail_button_clickable_text));
                    mBinding.goodsInfoDetailGoodsNoboxButton.setBackgroundResource(R.drawable.goods_info_detail_clickable_button);
                    mBinding.goodsInfoDetailGoodsBoxButton.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.goods_info_detail_button_unclickable_text));
                    mBinding.goodsInfoDetailGoodsBoxButton.setBackgroundResource(R.drawable.goods_info_detail_unclickable_button);
                }

            }
        });
        mBinding.goodsInfoDetailGoodsBoxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!choooseTypeFlag) {
                    choooseTypeFlag = true;
                    mBinding.goodsInfoDetailGoodsNoboxButton.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.goods_info_detail_button_unclickable_text));
                    mBinding.goodsInfoDetailGoodsNoboxButton.setBackgroundResource(R.drawable.goods_info_detail_unclickable_button);
                    mBinding.goodsInfoDetailGoodsBoxButton.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.goods_info_detail_button_clickable_text));
                    mBinding.goodsInfoDetailGoodsBoxButton.setBackgroundResource(R.drawable.goods_info_detail_clickable_button);
                }
            }
        });

        mBinding.goodsInfoDetailGoodsNumberAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(mBinding.goodsInfoDetailGoodsNumberText.getText().toString());
                num++;
                mBinding.goodsInfoDetailGoodsNumberText.setText(num + "");
            }
        });

        mBinding.goodsInfoDetailGoodsNumberDecreaseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(mBinding.goodsInfoDetailGoodsNumberText.getText().toString());
                if (num > 0) {
                    num--;
                    mBinding.goodsInfoDetailGoodsNumberText.setText(num + "");
                }
            }
        });

        mBinding.goodsInfoDetailReturnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        return this;
    }
}
