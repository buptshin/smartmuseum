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
import com.example.smartmuseum.model.Parameters;
import com.example.smartmuseum.util.ScreenUtil;
import com.example.smartmuseum.view.goods.GoodsInfoActivity;
import com.example.smartmuseum.view.goods.GoodsOrderCheckActivity;
import com.example.smartmuseum.view.goods.GoodsOrderStatusActivity;
import com.example.smartmuseum.view.navigation.NavigationGoRoutesActivity;
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
    protected void onResume() {
        super.onResume();
        if (Parameters.purchasedGoodsNum <= 0) {
            mBinding.routeCommendDragFloatButton.setBackgroundResource(R.mipmap.route_commend_info_pack_nothing);
        } else {
            mBinding.routeCommendDragFloatButton.setBackgroundResource(R.mipmap.route_commend_info_pack_something);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setPopupWindow();
    }

    @Override
    public RouteCommendInfoActivity bindData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_route_commend_info);
        //设置配送进度
        mBinding.routeCommendPopupwindowSomethingDeliveryTimeView.setScales(new double[]{0.8, 0.2});
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

        //关闭浮窗
        mBinding.routeCommendPopupwindowNothingCloseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.routeCommendPopupwindowNothingLayout.setVisibility(View.GONE);
                mBinding.routeCommendInfoLayout.setBackgroundResource(R.color.mainpage_goods_sell_background);

//                mBinding.routeCommendDragFloatButton.setVisibility(View.VISIBLE);

                //还原悬浮按钮显示的图片
                if (Parameters.purchasedGoodsNum <= 0) {
                    mBinding.routeCommendDragFloatButton.setBackgroundResource(R.mipmap.route_commend_info_pack_nothing);
                } else {
                    mBinding.routeCommendDragFloatButton.setBackgroundResource(R.mipmap.route_commend_info_pack_something);
                }
            }
        });

        //关闭浮窗
        mBinding.routeCommendPopupwindowSomethingCloseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.routeCommendPopupwindowSomethingLayout.setVisibility(View.GONE);
                mBinding.routeCommendInfoLayout.setBackgroundResource(R.color.mainpage_goods_sell_background);
//                mBinding.routeCommendDragFloatButton.setVisibility(View.VISIBLE);

                //还原悬浮按钮显示的图片
                if (Parameters.purchasedGoodsNum <= 0) {
                    mBinding.routeCommendDragFloatButton.setBackgroundResource(R.mipmap.route_commend_info_pack_nothing);
                } else {
                    mBinding.routeCommendDragFloatButton.setBackgroundResource(R.mipmap.route_commend_info_pack_something);
                }
            }
        });

        //悬浮按钮点击打开浮窗
        mBinding.routeCommendDragFloatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                mBinding.routeCommendDragFloatButton.setVisibility(View.GONE);
                //悬浮按钮设置成悬浮图片
                mBinding.routeCommendDragFloatButton.setBackgroundResource(R.mipmap.route_commend_info_pack_suspension);
                mBinding.routeCommendInfoLayout.setBackgroundResource(R.color.route_commend_info_popupwindow_parent_background);

                //购买数大于0，启动已购买浮窗。否则，启动推荐浮窗
                if (Parameters.purchasedGoodsNum <= 0) {
                    getCommendGoods();
                    mBinding.routeCommendPopupwindowNothingLayout.setVisibility(View.VISIBLE);
                } else {
                    mBinding.routeCommendPopupwindowSomethingLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        //悬浮按钮长按打开弹窗
        mBinding.routeCommendDragFloatButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                setDialog();
                return false;
            }
        });

        //推荐浮窗，选择物品查看详情
        mBinding.routeCommendPopupwindowAddGoodsPackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RouteCommendInfoActivity.this, GoodsInfoActivity.class);
                startActivity(intent);
            }
        });

        //已购买浮窗，查看订单信息
        mBinding.routeCommendPopupwindowSomethingGoodsDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RouteCommendInfoActivity.this, GoodsOrderStatusActivity.class);
                startActivity(intent);
            }
        });


        //返回按钮
        mBinding.routeCommendInfoReturnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //导航界面
        mBinding.routeCommendInfoMapImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RouteCommendInfoActivity.this, NavigationGoRoutesActivity.class);
                startActivity(intent);
            }
        });


        return this;
    }

    //设置弹窗
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
                dialog.dismiss();
            }
        });

    }

    //获取推荐物品数据
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

    //设置悬浮按钮颜色
    private void setPopupWindow() {
        //购买数大于0，显示已购买界面
        if (Parameters.purchasedGoodsNum <= 0) {
            mBinding.routeCommendDragFloatButton.setBackgroundResource(R.mipmap.route_commend_info_pack_nothing);
        } else {
            mBinding.routeCommendDragFloatButton.setBackgroundResource(R.mipmap.route_commend_info_pack_something);
            mBinding.routeCommendPopupwindowNothingLayout.setVisibility(View.GONE);
            mBinding.routeCommendPopupwindowSomethingLayout.setVisibility(View.VISIBLE);
        }

    }
}
