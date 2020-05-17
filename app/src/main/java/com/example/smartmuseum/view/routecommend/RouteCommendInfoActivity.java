package com.example.smartmuseum.view.routecommend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityRouteCommendInfoBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;
import com.example.smartmuseum.view.goods.GoodsOrderCheckActivity;

public class RouteCommendInfoActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityRouteCommendInfoBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public RouteCommendInfoActivity bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_route_commend_info);
        return this;
    }

    @Override
    public RouteCommendInfoActivity bindView() {
        //状态栏字体设为黑色
        ScreenUtil.setAndroidNativeLightStatusBar(RouteCommendInfoActivity.this, true);
        return this;
    }

    @Override
    public RouteCommendInfoActivity bindEvent() {
        return this;
    }
}
