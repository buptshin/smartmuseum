package com.example.smartmuseum.view.mainpage;

import android.app.FragmentManager;
import android.content.Intent;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.MainPageNavigationPagerAdapter;
import com.example.smartmuseum.databinding.FragmentMainpageExploreBinding;
import com.example.smartmuseum.databinding.FragmentMainpageNavigationBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.NavigationFlagModel;
import com.example.smartmuseum.view.exhibition.ExhibitionInnerCollectionFragment;
import com.example.smartmuseum.view.navigation.NavigationSearchActivity;
import com.example.smartmuseum.view.otherview.NoScrollViewPager;

import java.util.ArrayList;

/*导览fragment*/
public class MainPageNavigationFragment extends Fragment implements ViewChainedBinding {

    private FragmentMainpageNavigationBinding mBinding;

    //标记当前fragment是否已绑定xml文件
    protected boolean isCreated = false;

    private NavigationFlagModel flagModel;

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

        return this;
    }

    @Override
    public MainPageNavigationFragment bindEvent() {
        //进入当前楼层地图
        mBinding.mainpageNavigationLocationMapIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View parent = mBinding.getRoot().getRootView();
                NoScrollViewPager noScrollViewPager = (NoScrollViewPager)parent.findViewById(R.id.mainpage_noscrollviewpager);
                noScrollViewPager.setCurrentItem(8,false);
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

        //搜索按钮
        mBinding.mainpageNavigationSearchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NavigationSearchActivity.class);
                startActivity(intent);
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
            //将导览viewpage恢复到楼层导览
        }
    }


}
