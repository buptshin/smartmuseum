package com.example.smartmuseum.view.navigation;


import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentNavigationFloorsBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.NavigationFlagModel;
import com.example.smartmuseum.view.otherview.NoScrollViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFloorsFragment extends Fragment implements ViewChainedBinding {

    private FragmentNavigationFloorsBinding mBinding;
    //标记当前fragment是否已绑定xml文件
    protected boolean isCreated = false;

    private NavigationFlagModel flagModel;

    public static NavigationFloorsFragment getInstance(){
        return new NavigationFloorsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_navigation_floors,
                container,
                false);
        flagModel = ViewModelProviders.of(this).get(NavigationFlagModel.class);
        flagModel = new NavigationFlagModel();
        mBinding.setData(flagModel);
        mBinding.setLifecycleOwner(this);
        this.bindData().bindView().bindEvent();
        isCreated = true;
        return mBinding.getRoot();
    }

    @Override
    public NavigationFloorsFragment bindView() {
        return this;
    }

    @Override
    public NavigationFloorsFragment bindData() {
        return this;
    }

    @Override
    public NavigationFloorsFragment bindEvent() {
        //进入当前楼层地图
        mBinding.mainpageNavigationLocationMapIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View parent = mBinding.getRoot().getRootView();
                NoScrollViewPager noScrollViewPager = (NoScrollViewPager)parent.findViewById(R.id.mainpage_navigation_sv);
                noScrollViewPager.setCurrentItem(1,false);
            }
        });
        return this;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (!isCreated) {
            return;
        }

        if (isVisibleToUser) {
            //将导览viewpage恢复到楼层导览
        }
    }
}
