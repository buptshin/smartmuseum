package com.example.smartmuseum.view.goods;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

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

    //singtask模式，购买成功时，清空该Activity任务栈并调用
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        Toast.makeText(getApplicationContext(), "测试", Toast.LENGTH_LONG).show();
        setDialog();
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

    public void setDialog() {
        //创建View对象与XML关联
        LayoutInflater insertLayoutInflater = LayoutInflater.from(mBinding.getRoot().getContext());
        View successView = insertLayoutInflater.inflate(R.layout.dialog_goods_info_pay_success, null);
        //将View设置到Dialog中
        AlertDialog.Builder builder = new AlertDialog.Builder(mBinding.getRoot().getContext());
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setContentView(successView);
        ImageView closeImg = dialog.findViewById(R.id.dialog_goods_info_pay_success_close_img);
        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
