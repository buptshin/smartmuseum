package com.example.smartmuseum.view.navigation.exhibition;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ExhibitionInnerCollectorPopwindowViewBinding;
import com.example.smartmuseum.databinding.FragmentExhibitionInnerCollectionBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.LazyLocationFragment;
import com.example.smartmuseum.view.GlobalVariables;
import com.example.smartmuseum.view.otherview.NoScrollViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExhibitionInnerCollectionFragment extends LazyLocationFragment implements ViewChainedBinding {

    private FragmentExhibitionInnerCollectionBinding mBinding;
    private ImageView iv;
    private Matrix normalMatrix;
    private float imgX,imgY;
    private PopupWindow popupWindow;
    private int seekBarProgress;

    public ExhibitionInnerCollectionFragment() {
        // Required empty public constructor
    }

    public static ExhibitionInnerCollectionFragment getInstance(){
        return new ExhibitionInnerCollectionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_exhibition_inner_collection,
                container,
                false);
        this.bindData().bindView().bindEvent();
        return mBinding.getRoot();
    }

    @Override
    public ExhibitionInnerCollectionFragment bindView() {
        // 禁止seekBar的手动滑动
        mBinding.exhibitionInnerCollectionSeekbar.setEnabled(false);
        mBinding.exhibitionInnerCollectionSeekbar.setClickable(false);
        mBinding.exhibitionInnerCollectionSeekbar.setFocusable(false);
        return this;
    }

    @Override
    public ExhibitionInnerCollectionFragment bindData() {
        // 保存一份地图矩阵的原始矩阵
        normalMatrix = mBinding.exhibitionInnerCollectionMap.getImageMatrix();

        // 设定seekBar的初始值
        seekBarProgress = 3;
        return this;
    }

    // 放大和缩小一次就够了，别点了，地图可以正常变化，但是定位点不行
    @Override
    public ExhibitionInnerCollectionFragment bindEvent() {

        // “放大”
        mBinding.exhibitionInnerCollectionExpandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBarProgress += 3;
                if(seekBarProgress > 6)
                    return;
                mBinding.exhibitionInnerCollectionSeekbar.setProgress(seekBarProgress);
                // 将地图放大
                Matrix matrix = normalMatrix;
                matrix.postScale((float)1.6,(float)1.6);   // 默认扩大1.6倍（主要是方便还原的倍数设定）
                mBinding.exhibitionInnerCollectionMap.setImageMatrix(matrix);
            }
        });


        // “缩小”
        mBinding.exhibitionInnerCollectionSmallImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBarProgress -= 3;
                if(seekBarProgress <= 0){
                    View parent = mBinding.getRoot().getRootView();
                    NoScrollViewPager noScrollViewPager = (NoScrollViewPager)parent.findViewById(R.id.mainpage_navigation_sv);
                    noScrollViewPager.setCurrentItem(1,false);
                }else {
                    mBinding.exhibitionInnerCollectionSeekbar.setProgress(seekBarProgress);
                    // 缩小地图
                    Matrix matrix = normalMatrix;
                    Matrix changeMatrix = new Matrix();
                    changeMatrix.postScale((float)0.625,(float)0.625); // 用于变换坐标点的矩阵
                    matrix.postScale((float)0.625,(float)0.625);  // 用于地图缩放的矩阵
                    mBinding.exhibitionInnerCollectionMap.setImageMatrix(matrix);
                }
            }
        });
        return this;
    }

    @Override
    protected void onSubscribe() {
    }

    @Override
    protected void cancelSubscribe() {
    }
}
