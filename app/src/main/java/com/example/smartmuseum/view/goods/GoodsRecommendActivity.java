package com.example.smartmuseum.view.goods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.MainPageGoodsCommendAdapter;
import com.example.smartmuseum.databinding.ActivityGoodsrecommendBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.Goods;
import com.example.smartmuseum.model.Location;
import com.example.smartmuseum.util.ScreenUtil;
import com.example.smartmuseum.viewmodel.GoodsViewModel;
import com.example.smartmuseum.viewmodel.LocationViewModel;

import java.util.HashMap;
import java.util.List;

public class GoodsRecommendActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityGoodsrecommendBinding mBinding;
    private GoodsViewModel goodsViewModel;
    private LocationViewModel locationViewModel;
    private List<Goods> goodsList;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public GoodsRecommendActivity bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_goodsrecommend);
        goodsViewModel = new ViewModelProvider(this).get(GoodsViewModel.class);
        locationViewModel = new ViewModelProvider(this).get(LocationViewModel.class);

        //获取当前位置
        HashMap<String, String> locationMap = new HashMap<>();

        //Activity调用this, fragment调用getViewLifecycleOwner()
        locationViewModel.getNowLocationModel(locationMap).observe(this, models -> {
            location = models;
            mBinding.setLocation(location);
        });


        //获取推荐商品
        HashMap<String, String> goodsMap = new HashMap<>();
        //Activity调用this, fragment调用getViewLifecycleOwner()
        goodsViewModel.getCommendGoodsModelList(goodsMap).observe(this, models -> {
            goodsList = models;
            LinearLayoutManager ms = new LinearLayoutManager(this);
            ms.setOrientation(LinearLayoutManager.HORIZONTAL);
            mBinding.mainpageGoodsSellCommendRecyclerview.setLayoutManager(ms);
            mBinding.mainpageGoodsSellCommendRecyclerview.setAdapter(new MainPageGoodsCommendAdapter(goodsList));
        });



        return this;
    }

    @Override
    public GoodsRecommendActivity bindView() {
        //状态栏字体设为黑色
        ScreenUtil.setAndroidNativeLightStatusBar(GoodsRecommendActivity.this, true);
        return this;
    }

    @Override
    public GoodsRecommendActivity bindEvent() {

        mBinding.mainpageGoodsSellCommendSelectDislikeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mBinding.mainpageGoodsSellCommendShopImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        return this;
    }
}
