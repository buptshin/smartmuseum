package com.example.smartmuseum.view.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentNavigationFirstAidBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.view.otherview.NoScrollViewPager;

public class NavigationFirstAidFragment extends Fragment implements ViewChainedBinding {

    private FragmentNavigationFirstAidBinding mBinding;

    public NavigationFirstAidFragment() {
        // Required empty public constructor
    }

    public static NavigationFirstAidFragment getInstance(){
        return new NavigationFirstAidFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_exhibition_inner_collection, container, false);
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_navigation_first_aid,
                container,
                false);
        this.bindData().bindView().bindEvent();
        return mBinding.getRoot();
    }
    @Override
    public NavigationFirstAidFragment bindView() {
//        View parent = mBinding.getRoot().getRootView();
//        ImageView navEscapeIV = (ImageView)parent.findViewById(R.id.mainpage_navigation_escape_routes_iv);
//        ImageView navLocationIV = (ImageView)parent.findViewById(R.id.mainpage_navigation_now_location_iv);
//        navEscapeIV.setVisibility(View.GONE);
//        navLocationIV.setVisibility(View.GONE);
//        parent.setBackgroundColor(getResources().getColor(R.color.mainpage_navigation_red));
        return this;
    }

    @Override
    public NavigationFirstAidFragment bindData() {
        return this;
    }

    @Override
    public NavigationFirstAidFragment bindEvent() {
        //求救
        mBinding.mainpageNavigationCallHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ConfirmHelpActivity.class);
                startActivity(intent);
            }
        });

        mBinding.mainpageNavigationFirstaidNowLocationIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View parent = mBinding.getRoot().getRootView();
                NoScrollViewPager noScrollViewPager = (NoScrollViewPager)parent.findViewById(R.id.mainpage_noscrollviewpager);
                noScrollViewPager.setCurrentItem(1,false);
            }
        });

        return this;
    }
}
