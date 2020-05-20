package com.example.smartmuseum.view.navigation;

import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.example.smartmuseum.view.mainpage.MainPageNavigationFragment;
import com.example.smartmuseum.view.otherview.NoScrollViewPager;

/*导览-当前楼层fragment*/
public class NavigationNowFloorFragment extends Fragment implements ViewChainedBinding {

    private FragmentNavigationNowFloorBinding mBinding;

    //标记当前fragment是否已绑定xml文件
    protected boolean isCreated = false;

    private NavigationFlagModel flagModel;

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

                }else if (flagModel.getZoomValue().getValue().equals(0)){
                    mBinding.mainpageNavigationProcessIv.setImageResource(R.mipmap.mainpage_navigation_progressbar_middle);
                    if (isZoom){
                        // 缩小地图
                        Matrix matrix = normalMatrix;
                        Matrix changeMatrix = new Matrix();
                        changeMatrix.postScale((float)0.625,(float)0.625); // 用于变换坐标点的矩阵
                        matrix.postScale((float)0.625,(float)0.625);  // 用于地图缩放的矩阵
                        mBinding.mainpageNavigationLocationMapIv.setImageMatrix(matrix);
                        isZoom = false;
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
                    //防止是从展厅地图跳转过来，引起二次放大
                    if (!isZoom){
                        mBinding.mainpageNavigationProcessIv.setImageResource(R.mipmap.mainpage_navigation_progressbar_high);
                        //放大地图
                        // 保存一份地图矩阵的原始矩阵
                        Matrix matrix = normalMatrix;
                        matrix.postScale((float)1.6,(float)1.6);   // 默认扩大1.6倍（主要是方便还原的倍数设定）
                        mBinding.mainpageNavigationLocationMapIv.setImageMatrix(matrix);
                        isZoom = true;
                    }


                }else if (flagModel.getZoomValue().getValue().equals(2)){
                    View parent = mBinding.getRoot().getRootView();
                    NoScrollViewPager noScrollViewPager = (NoScrollViewPager)parent.findViewById(R.id.mainpage_noscrollviewpager);
                    noScrollViewPager.setCurrentItem(6,false);
                }
            }
        });
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

        //搜索按钮
        mBinding.mainpageNavigationSearchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NavigationSearchActivity.class);
                startActivity(intent);
            }
        });

        //进入急救路线地图
        mBinding.mainpageNavigationEscapeRoutesIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View parent = mBinding.getRoot().getRootView();
                NoScrollViewPager noScrollViewPager = (NoScrollViewPager)parent.findViewById(R.id.mainpage_noscrollviewpager);
                noScrollViewPager.setCurrentItem(7,false);
            }
        });
        return this;
    }

    /*
     * 此方法目前仅适用于标示ViewPager中的Fragment是否真实可见
     * */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (!isCreated) {
            return;
        }

        if (isVisibleToUser) {
        }
    }



}
