package com.example.smartmuseum.view.navigation;

import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentNavigationNowFloorBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.NavigationFlagModel;
import com.example.smartmuseum.util.LazyLocationFragment;
import com.example.smartmuseum.view.mainpage.MainPageNavigationFragment;
import com.example.smartmuseum.view.otherview.NoScrollViewPager;

/*导览-当前楼层fragment*/
public class NavigationNowFloorFragment extends LazyLocationFragment implements ViewChainedBinding {

    private FragmentNavigationNowFloorBinding mBinding;

    //标记当前fragment是否已绑定xml文件
    protected boolean isCreated = false;

    public NavigationFlagModel flagModel;

    Matrix normalMatrix;

    //标记地图是否被放大过
    private boolean isZoom = false;

    public NavigationNowFloorFragment() {
        // Required empty public constructor
    }

    public static NavigationNowFloorFragment getInstance() {
        NavigationNowFloorFragment fragment = new NavigationNowFloorFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_navigation_now_floor,
                container,
                false);
        flagModel = ViewModelProviders.of(this).get(NavigationFlagModel.class);
        flagModel = new NavigationFlagModel();
        mBinding.setData(flagModel);
        mBinding.setLifecycleOwner(this);
        View v = mBinding.getRoot();
        // 标记当前fragment是否已绑定xml文件
        isCreated = true;

        this.bindData().bindView().bindEvent();
        return v;
    }


    @Override
    public NavigationNowFloorFragment bindView() {
        return this;
    }

    @Override
    public NavigationNowFloorFragment bindData() {
        // 保存一份地图矩阵的原始矩阵
        normalMatrix = mBinding.mainpageNavigationLocationMapIv.getImageMatrix();
        return this;
    }

    @Override
    public NavigationNowFloorFragment bindEvent() {
        //监听是否打开筛选
        flagModel.getIsGreen().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (flagModel.getIsGreen().getValue().equals(false) && flagModel.getZoomValue().getValue() == 0){
                    mBinding.mainpageNavigationScreenIv.setImageResource(R.mipmap.mainpage_navigation_screen_false);
                    mBinding.mainpageNavigationLocationMapIv.setImageResource(R.drawable.mainpage_navigation_floor_map);
                }else if (flagModel.getIsGreen().getValue().equals(true) && flagModel.getZoomValue().getValue() == 0){
                    mBinding.mainpageNavigationScreenIv.setImageResource(R.mipmap.mainpage_navigation_screen_true);
                    mBinding.mainpageNavigationLocationMapIv.setImageResource(R.drawable.mainpage_navigation_floor_map_icon);
                }else {
                    flagModel.getIsGreen().setValue(true);
                }
            }
        });

        //监听缩放
        flagModel.getZoomValue().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                if (flagModel.getZoomValue().getValue().equals(-1)){
                    //将导览viewpage恢复到楼层导览
                    View parent = mBinding.getRoot().getRootView();
                    NoScrollViewPager noScrollViewPager = (NoScrollViewPager)parent.findViewById(R.id.mainpage_noscrollviewpager);
                    noScrollViewPager.setCurrentItem(1,false);
                    flagModel.getZoomValue().setValue(0);
                    flagModel.getIsGreen().setValue(false);
                    isZoom = false;

                }else if (flagModel.getZoomValue().getValue().equals(0)){
                    mBinding.mainpageNavigationProcessIv.setImageResource(R.mipmap.mainpage_navigation_progressbar_middle);
                    if (isZoom){
                        isZoom = false;
                        // 缩小地图
                        Matrix matrix = normalMatrix;
                        matrix.postScale((float)0.625,(float)0.625);  // 用于地图缩放的矩阵
                        mBinding.mainpageNavigationLocationMapIv.setImageMatrix(matrix);

                    }else {
                        if (flagModel.getIsGreen().getValue().equals(false)){
                            //正常大小、无筛的地图
                            mBinding.mainpageNavigationLocationMapIv.setImageResource(R.drawable.mainpage_navigation_floor_map);
                        }else if (flagModel.getIsGreen().getValue().equals(true)){
                            //正常大小、有筛地图
                            mBinding.mainpageNavigationLocationMapIv.setImageResource(R.drawable.mainpage_navigation_floor_map_icon);
                        }
                    }
                }else if (flagModel.getZoomValue().getValue().equals(1)){
                    mBinding.mainpageNavigationProcessIv.setImageResource(R.mipmap.mainpage_navigation_progressbar_high);
                    //防止是从展厅地图跳转过来，引起二次放大
                    if (!isZoom){
                        //放大地图
                        // 保存一份地图矩阵的原始矩阵
                        Matrix matrix = normalMatrix;
                        matrix.postScale((float)1.6,(float)1.6);   // 默认扩大1.6倍（主要是方便还原的倍数设定）
                        mBinding.mainpageNavigationLocationMapIv.setImageMatrix(matrix);
                        isZoom = true;
                    }
                }else if (flagModel.getZoomValue().getValue().equals(2)){
                    //跳转到展厅界面
                    View parent = mBinding.getRoot().getRootView();
                    NoScrollViewPager noScrollViewPager = (NoScrollViewPager)parent.findViewById(R.id.mainpage_navigation_sv);
                    noScrollViewPager.setCurrentItem(2,false);
                }
            }
        });
        return this;
    }

    /*
     * 此方法目前仅适用于标示ViewPager中的Fragment是否真实可见
     * */
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//
//        if (!isCreated) {
//            return;
//        }
//
//        if (isVisibleToUser) {
//
//            isZoom = false;
//
//            flagModel.getZoomValue().setValue(0);
//            flagModel.getIsGreen().setValue(false);
//
//        }
//    }


    @Override
    protected void additionalWork() {
        isZoom = false;

        flagModel.getZoomValue().setValue(0);
        flagModel.getIsGreen().setValue(false);
    }

    @Override
    protected void onSubscribe() {
    }

    @Override
    protected void cancelSubscribe() {
    }
}
