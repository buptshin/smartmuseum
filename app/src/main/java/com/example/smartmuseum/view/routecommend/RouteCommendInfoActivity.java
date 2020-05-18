package com.example.smartmuseum.view.routecommend;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.GoodsCommendAdapter;
import com.example.smartmuseum.databinding.ActivityRouteCommendInfoBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.Goods;
import com.example.smartmuseum.util.ScreenUtil;
import com.example.smartmuseum.view.goods.GoodsInfoActivity;
import com.example.smartmuseum.view.goods.GoodsOrderCheckActivity;
import com.example.smartmuseum.viewmodel.GoodsViewModel;

import java.util.HashMap;
import java.util.List;

public class RouteCommendInfoActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityRouteCommendInfoBinding mBinding;
    private List<Goods> goodsCommendList;

    private GoodsViewModel goodsCommendViewModel;

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
        setDialog();
        return this;
    }

    @Override
    public RouteCommendInfoActivity bindEvent() {

        mBinding.routeCommendInfoFirstLocationImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.routeCommendPopupwindowNothingLayout.setVisibility(View.VISIBLE);
                getCommendGoods();
            }
        });
        return this;
    }

    public void setDialog() {
        //创建View对象与XML关联
        LayoutInflater insertLayoutInflater = LayoutInflater.from(mBinding.getRoot().getContext());
        View successView = insertLayoutInflater.inflate(R.layout.dialog_route_commend_goods, null);
        //将View设置到Dialog中
        AlertDialog.Builder builder = new AlertDialog.Builder(mBinding.getRoot().getContext());
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setContentView(successView);

        ImageView closeImg = dialog.findViewById(R.id.dialog_route_commend_goods_close_img);
        Button detailButton = dialog.findViewById(R.id.dialog_route_commend_goods_detail_button);

        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RouteCommendInfoActivity.this, GoodsInfoActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getCommendGoods() {
        goodsCommendViewModel = new ViewModelProvider(this).get(GoodsViewModel.class);
        HashMap<String, String> map = new HashMap<>();
        //Activity调用this, fragment调用getViewLifecycleOwner()
        goodsCommendViewModel.getRouteCommendGoodsModelList(map).observe(this, models -> {
            goodsCommendList = models;
            LinearLayoutManager ms = new LinearLayoutManager(mBinding.getRoot().getContext());
            ms.setOrientation(LinearLayoutManager.HORIZONTAL);
            mBinding.routeCommendPopupwindowCommendGoodsRecyclerview.setLayoutManager(ms);
            mBinding.routeCommendPopupwindowCommendGoodsRecyclerview.setAdapter(new GoodsCommendAdapter(goodsCommendList));
        });
    }
}
