package com.example.smartmuseum.view.exhibition;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Matrix;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentExhibitionInnerCollectionBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.view.GlobalVariables;
import com.example.smartmuseum.view.mainpage.MainPageNavigationFragment;
import com.example.smartmuseum.view.otherview.NoScrollViewPager;

import javax.xml.transform.sax.TemplatesHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExhibitionInnerCollectionFragment extends Fragment implements ViewChainedBinding {

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
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_exhibition_inner_collection, container, false);
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

        // 绘制出定位点的渲染圆圈（需要在界面上已经绘制出定位点之后才能确定位置）
        final ViewTreeObserver locObserver = mBinding.exhibitionInnerCollectionLocpoint.getViewTreeObserver();
        locObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 保存一份定位点的原始位置
                imgX = mBinding.exhibitionInnerCollectionLocpoint.getX();
                imgY = mBinding.exhibitionInnerCollectionLocpoint.getY();

                // 绘制出定位点周围的渲染
                float x = mBinding.exhibitionInnerCollectionLocpoint.getX()+(float)mBinding.exhibitionInnerCollectionLocpoint.getWidth()/2;
                float y = mBinding.exhibitionInnerCollectionLocpoint.getY()+(float)mBinding.exhibitionInnerCollectionLocpoint.getHeight()/2;
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(100,100);
                iv = new ImageView(getContext());
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                iv.setImageResource(R.drawable.exhibition_inner_collector_locpoint_1);
                iv.setLayoutParams(params);
                iv.setX(x-50);
                iv.setY(y-50);
                mBinding.exhibitionInnerCollectionCl.addView(iv);

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

                // 移除监控
                mBinding.exhibitionInnerCollectionLocpoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
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

                // 放大定位点
                ObjectAnimator objectAnimatorLocX = ObjectAnimator.ofFloat(mBinding.exhibitionInnerCollectionLocpoint,"scaleX",1f,1.6f);
                ObjectAnimator objectAnimatorLocY = ObjectAnimator.ofFloat(mBinding.exhibitionInnerCollectionLocpoint,"scaleY",1f,1.6f);
                AnimatorSet locExpand = new AnimatorSet();
                locExpand.setDuration(500);
                locExpand.play(objectAnimatorLocX).with(objectAnimatorLocY);
                locExpand.addListener(new AnimatorListenerAdapter() {
                    // 在定位点放大之后改变定位点的位置
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Matrix curMatrix = mBinding.exhibitionInnerCollectionMap.getImageMatrix();
                        // 计算定位点相对于地图的相对坐标之后再进行定位点的移动
                        float[] dstCoordsLoc = new float[2];  // dstCoordsLoc为新的相对坐标
                        float tx = (float)mBinding.exhibitionInnerCollectionLocpoint.getX()-mBinding.exhibitionInnerCollectionMap.getX();
                        float ty = (float)mBinding.exhibitionInnerCollectionLocpoint.getY()-mBinding.exhibitionInnerCollectionMap.getY();
                        curMatrix.mapPoints(dstCoordsLoc,new float[]{tx,ty});

                        // 改变定位点和周围的渲染的位置
                        ObjectAnimator obLocY = ObjectAnimator.ofFloat(mBinding.exhibitionInnerCollectionLocpoint,"translationY",mBinding.exhibitionInnerCollectionLocpoint.getY()-imgY+dstCoordsLoc[1]-ty);
                        ObjectAnimator obLocX = ObjectAnimator.ofFloat(mBinding.exhibitionInnerCollectionLocpoint,"translationX",mBinding.exhibitionInnerCollectionLocpoint.getX()-imgX+dstCoordsLoc[0]-tx);
                        ObjectAnimator obLocY1 = ObjectAnimator.ofFloat(iv,"translationY",iv.getY()+dstCoordsLoc[1]-ty);
                        ObjectAnimator obLocX1 = ObjectAnimator.ofFloat(iv,"translationX",iv.getX()+dstCoordsLoc[0]-tx);
                        AnimatorSet newLocPoint = new AnimatorSet();
                        newLocPoint.play(obLocX).with(obLocY).with(obLocX1).with(obLocY1);
                        newLocPoint.setDuration(2000);
                        newLocPoint.addListener(new AnimatorListenerAdapter() {
                            // 在得到新的位置后，移动定位点（模拟实际的参观人员的移动）
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                // 移动定位点(这里换用ValueAnimator是因为在移动的过程中，定位点周围的渲染的位置不好确定，采用ValueAnimator可以直接赋值，保持定位点和渲染的相对位置不变)
                                ValueAnimator locMove = ValueAnimator.ofFloat(mBinding.exhibitionInnerCollectionLocpoint.getY(),
                                        mBinding.exhibitionInnerCollectionLocpoint.getY()-680);
                                locMove.setDuration(5000);   // 模拟参观人员的实际走动时间
                                locMove.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                    @Override
                                    public void onAnimationUpdate(ValueAnimator animation) {
                                        Float m = (Float)animation.getAnimatedValue();
                                        mBinding.exhibitionInnerCollectionLocpoint.setY(m);
                                        iv.setY(m+mBinding.exhibitionInnerCollectionLocpoint.getHeight()/2-50);
                                        mBinding.exhibitionInnerCollectionLocpoint.requestLayout();
                                    }
                                });
                                // 在移动结束之后，出现展品的信息介绍弹窗
                                locMove.addListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        GlobalVariables.location_change = 1;  // 全局变量，标识用户的位置已经改变
                                        View view = getActivity().getLayoutInflater().inflate(R.layout.exhibition_inner_collector_popwindow_view,null);
                                        view.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);
                                        // 第四个参数是弹窗能否获得焦点
                                        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,true);
                                        popupWindow.setOutsideTouchable(true);
                                        popupWindow.showAsDropDown(mBinding.exhibitionInnerCollectionCheckImg);
                                    }
                                });
                                locMove.start();
                            }
                        });
                        newLocPoint.start();

                    }
                });
                locExpand.start();
            }
        });


        // “缩小”
        mBinding.exhibitionInnerCollectionSmallImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBarProgress -= 3;
                if(seekBarProgress <= 0){
                    View parent = mBinding.getRoot().getRootView();
                    NoScrollViewPager noScrollViewPager = (NoScrollViewPager)parent.findViewById(R.id.mainpage_noscrollviewpager);
                    noScrollViewPager.setCurrentItem(8,false);
                }else {
                    mBinding.exhibitionInnerCollectionSeekbar.setProgress(seekBarProgress);
                    // 缩小地图
                    Matrix matrix = normalMatrix;
                    Matrix changeMatrix = new Matrix();
                    changeMatrix.postScale((float)0.625,(float)0.625); // 用于变换坐标点的矩阵
                    matrix.postScale((float)0.625,(float)0.625);  // 用于地图缩放的矩阵
                    mBinding.exhibitionInnerCollectionMap.setImageMatrix(matrix);

                    // 缩小定位点
                    ObjectAnimator objectAnimatorX1 = ObjectAnimator.ofFloat(mBinding.exhibitionInnerCollectionLocpoint,"scaleX",1f);
                    ObjectAnimator objectAnimatorY1 = ObjectAnimator.ofFloat(mBinding.exhibitionInnerCollectionLocpoint,"scaleY",1f);
                    AnimatorSet locSmaller = new AnimatorSet();
                    locSmaller.setDuration(500);
                    locSmaller.play(objectAnimatorX1).with(objectAnimatorY1);
                    locSmaller.addListener(new AnimatorListenerAdapter() {
                        // 改变定位点和周围渲染的位置（在缩小后的地图上的位置）
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            float[] dstCoords = new float[2];
                            // 这里从逻辑上是有问题的，应该在地图缩放前，就计算出缩放后应有的坐标，但是这里因为地图的坐标其实是不会变的，并且以后也不是这样来计算坐标
                            float tx = (float)mBinding.exhibitionInnerCollectionLocpoint.getX()-mBinding.exhibitionInnerCollectionMap.getX();
                            float ty = (float)mBinding.exhibitionInnerCollectionLocpoint.getY()-mBinding.exhibitionInnerCollectionMap.getY();
                            changeMatrix.mapPoints(dstCoords,new float[]{tx,ty});
                            ObjectAnimator obLocY = ObjectAnimator.ofFloat(mBinding.exhibitionInnerCollectionLocpoint,"translationY",dstCoords[1]-imgY);
                            ObjectAnimator obLocX = ObjectAnimator.ofFloat(mBinding.exhibitionInnerCollectionLocpoint,"translationX",dstCoords[0]-imgX);
                            ObjectAnimator obLocY1 = ObjectAnimator.ofFloat(iv,"translationY",iv.getY()+dstCoords[1]-ty);
                            ObjectAnimator obLocX1 = ObjectAnimator.ofFloat(iv,"translationX",iv.getX()+dstCoords[0]-tx);
                            AnimatorSet newLocPoint = new AnimatorSet();
                            newLocPoint.play(obLocX).with(obLocY).with(obLocX1).with(obLocY1);
                            newLocPoint.setDuration(2000);
                            newLocPoint.start();
                        }
                    });
                    locSmaller.start();
                }
            }
        });
        return this;
    }
}
