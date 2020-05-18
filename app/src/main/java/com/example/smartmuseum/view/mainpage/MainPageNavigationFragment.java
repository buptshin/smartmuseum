package com.example.smartmuseum.view.mainpage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.MainPageNavigationPagerAdapter;
import com.example.smartmuseum.databinding.FragmentMainpageExploreBinding;
import com.example.smartmuseum.databinding.FragmentMainpageNavigationBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.NavigationFlagModel;

import java.util.ArrayList;

/*导览fragment*/
public class MainPageNavigationFragment extends Fragment implements ViewChainedBinding {

    private FragmentMainpageNavigationBinding mBinding;
    private ArrayList<View> viewList;
    private MainPageNavigationPagerAdapter mAdapter;
    //标记当前fragment是否已绑定xml文件
    protected boolean isCreated = false;
    private NavigationFlagModel flagModel;

    /** 标记筛选按钮是否打开*/
    private boolean isScreen = false;

    /*
    * 标记当前地图放大倍数，默认0，最大3
    * 0：楼层导览
    * 1：当前楼层地图
    * 2：当前楼层地图放大模式
    * 3：文物导览模式
    * */
    private int zoomValue = 0;

    public MainPageNavigationFragment() {
    }


    public static MainPageNavigationFragment getInstance() {
        MainPageNavigationFragment fragment = new MainPageNavigationFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_mainpage_navigation,
                container,
                false);
        flagModel = ViewModelProviders.of(this).get(NavigationFlagModel.class);
        flagModel = new NavigationFlagModel();
        mBinding.setData(flagModel);
        mBinding.setLifecycleOwner(this);
        flagModel.getIsGreen().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (flagModel.getIsGreen().equals(false)){
                    mBinding.mainpageNavigationScreenIv.setImageResource(R.mipmap.mainpage_navigation_screen_false);
                    mBinding.mainpageNavigationMapViewpager.setCurrentItem(0);

                }else if (flagModel.getIsGreen().equals(true)){
                    mBinding.mainpageNavigationScreenIv.setImageResource(R.mipmap.mainpage_navigation_screen_true);
                    mBinding.mainpageNavigationMapViewpager.setCurrentItem(1);
                }

            }
        });
        flagModel.getZoomValue().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (flagModel.getZoomValue().equals(0)){
                    mBinding.mainpageNavigationProcessIv.setImageResource(R.mipmap.mainpage_navigation_progressbar_middle);
                    if (flagModel.getIsGreen().equals(true)){
                        mBinding.mainpageNavigationMapViewpager.setCurrentItem(1);
                    }else if (flagModel.getIsGreen().equals(false)){
                        mBinding.mainpageNavigationMapViewpager.setCurrentItem(0);
                    }

                }else if (flagModel.getZoomValue().equals(1)){
                    mBinding.mainpageNavigationProcessIv.setImageResource(R.mipmap.mainpage_navigation_progressbar_high);
                    mBinding.mainpageNavigationMapViewpager.setCurrentItem(2);
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
    public MainPageNavigationFragment bindData() {
        return this;
    }

    @Override
    public MainPageNavigationFragment bindView() {
        mBinding.mainpageNavigationNormal.setVisibility(View.VISIBLE);
        mBinding.mainpageNavigationFirstaid.setVisibility(View.GONE);
        viewList = new ArrayList<View>();
        LayoutInflater li = getLayoutInflater();
        viewList.add(li.inflate(R.layout.navigation_map_item1,null,false));
        viewList.add(li.inflate(R.layout.navigation_map_item2,null,false));
        viewList.add(li.inflate(R.layout.navigation_map_item3,null,false));
        mAdapter = new MainPageNavigationPagerAdapter(viewList);
        mBinding.mainpageNavigationMapViewpager.setAdapter(mAdapter);
        return this;
    }

    @Override
    public MainPageNavigationFragment bindEvent() {
        //进入当前楼层地图
        mBinding.mainpageNavigationLocationMapIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.mainpageNavigationNormalNowLocationCl.setVisibility(View.GONE);
                mBinding.mainpageNavigationZoomCl.setVisibility(View.VISIBLE);
                mBinding.mainpageNavigationLocationMapIv.setVisibility(View.GONE);
                mBinding.mainpageNavigationMapViewpager.setVisibility(View.VISIBLE);

            }
        });

        //进入急救路线地图
        mBinding.mainpageNavigationEscapeRoutesIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.mainpageNavigationNormal.setVisibility(View.GONE);
                mBinding.mainpageNavigationFirstaid.setVisibility(View.VISIBLE);
            }
        });

        /*//筛选开/关mainpage_navigation_screen_iv
        mBinding.mainpageNavigationScreenIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isScreen){
                    isScreen = true;
                    mBinding.mainpageNavigationScreenIv.setImageResource(R.mipmap.mainpage_navigation_screen_true);
                    mBinding.mainpageNavigationMapViewpager.setCurrentItem(1);
                }else{
                    isScreen = false;
                    mBinding.mainpageNavigationScreenIv.setImageResource(R.mipmap.mainpage_navigation_screen_false);
                    mBinding.mainpageNavigationMapViewpager.setCurrentItem(0);
                }

            }
        });

        //缩小按钮mainpage_navigation_narrow_iv
        mBinding.mainpageNavigationNarrowIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //放大按钮mainpage_navigation_process_iv
        mBinding.mainpageNavigationEnlargeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomValue ++;
                if (zoomValue == 1){

                }

            }
        });

        //搜索按钮
        mBinding.mainpageNavigationSearchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //急救地图-搜索按钮
        mBinding.mainpageNavigationFirstaidSearchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });*/
        return null;
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
            //将导览viewpage恢复到楼层导览
            mBinding.mainpageNavigationNormal.setVisibility(View.VISIBLE);
            mBinding.mainpageNavigationFirstaid.setVisibility(View.GONE);
            mBinding.mainpageNavigationNormalNowLocationCl.setVisibility(View.VISIBLE);
            mBinding.mainpageNavigationZoomCl.setVisibility(View.GONE);
            mBinding.mainpageNavigationLocationMapIv.setVisibility(View.VISIBLE);
            mBinding.mainpageNavigationMapViewpager.setVisibility(View.GONE);
        }
    }


}
