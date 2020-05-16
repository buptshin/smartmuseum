package com.example.smartmuseum.view.goods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.GoodsChooseAddressAdapter;
import com.example.smartmuseum.databinding.ActivityGoodsChooseAddressBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.User;
import com.example.smartmuseum.util.ScreenUtil;
import com.example.smartmuseum.viewmodel.UserViewModel;

import java.util.HashMap;

public class GoodsChooseAddressActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityGoodsChooseAddressBinding mBinding;
    private UserViewModel userViewModel;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_choose_address);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public GoodsChooseAddressActivity bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_goods_choose_address);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        HashMap<String, String> map = new HashMap<>();
        userViewModel.getAddressUserModel(map).observe(this, models -> {
            user = models;
            mBinding.goodsChooseAddressRecyclerview.setLayoutManager(new LinearLayoutManager(mBinding.getRoot().getContext()));
            mBinding.goodsChooseAddressRecyclerview.setAdapter(new GoodsChooseAddressAdapter(user.getAddress()));
        });
        return this;
    }

    @Override
    public GoodsChooseAddressActivity bindView() {
        //状态栏字体设为黑色
        ScreenUtil.setAndroidNativeLightStatusBar(GoodsChooseAddressActivity.this, true);
        return this;
    }

    @Override
    public GoodsChooseAddressActivity bindEvent() {
        mBinding.goodsChooseAddressReturnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        return this;
    }
}
