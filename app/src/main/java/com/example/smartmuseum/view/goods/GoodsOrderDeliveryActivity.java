package com.example.smartmuseum.view.goods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityGoodsOrderDeliveryBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;

public class GoodsOrderDeliveryActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityGoodsOrderDeliveryBinding mBinding;
    private ImageView iv;

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

        // 在“骑手定位”的位置已经确定后，画出周围的渲染点
        ViewTreeObserver carLocObserver = mBinding.goodsOrderDeliveryCarLocPoint.getViewTreeObserver();
        carLocObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 绘制出定位点周围的渲染
                float x = mBinding.goodsOrderDeliveryCarLocPoint.getX()+(float)mBinding.goodsOrderDeliveryCarLocPoint.getWidth()/2;
                float y = mBinding.goodsOrderDeliveryCarLocPoint.getY()+(float)mBinding.goodsOrderDeliveryCarLocPoint.getHeight()/2;
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(100,100);
                iv = new ImageView(GoodsOrderDeliveryActivity.this);
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                iv.setImageResource(R.drawable.exhibition_inner_collector_locpoint_1);
                iv.setLayoutParams(params);
                iv.setX(x-50);
                iv.setY(y-50);
                mBinding.goodsOrderDeliveryCt.addView(iv);

                // 渲染的动画展示
                ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(iv,"scaleX",1f,1.5f,2f,1.5f,1f);
                animatorScaleX.setRepeatCount(ValueAnimator.INFINITE);
                ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(iv,"scaleY",1f,1.5f,2f,1.5f,1f);
                animatorScaleY.setRepeatCount(ValueAnimator.INFINITE);
                ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(iv,"alpha",0.5f,0.7f,1f,0.7f,0.5f);
                animatorAlpha.setRepeatCount(ValueAnimator.INFINITE);
                AnimatorSet ivSet = new AnimatorSet();
                ivSet.play(animatorScaleX).with(animatorScaleY).with(animatorAlpha);
                ivSet.setDuration(3000);
                ivSet.start();

                // 模拟骑手的移动
                ObjectAnimator transCar = ObjectAnimator.ofFloat(mBinding.goodsOrderDeliveryCarLocPoint,"translationX",-400);
                ObjectAnimator transAnimate = ObjectAnimator.ofFloat(iv,"translationX",iv.getX()-400);
                AnimatorSet move = new AnimatorSet();
                move.setDuration(5000);
                move.play(transCar).with(transAnimate);
                move.start();

                mBinding.goodsOrderDeliveryCarLocPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
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
