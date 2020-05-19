package com.example.smartmuseum.view.explore;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentExploreRecommendedrouteBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.view.goods.GoodsInfoActivity;
import com.example.smartmuseum.view.routecommend.RouteCommendInfoActivity;

public class ExploreRecommendRoute extends Fragment implements ViewChainedBinding {
    private View view;
    private FragmentExploreRecommendedrouteBinding fragmentExploreRecommendedrouteBinding;

    public static ExploreRecommendRoute getInstance() {
        ExploreRecommendRoute fragment = new ExploreRecommendRoute();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentExploreRecommendedrouteBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_explore_recommendedroute,
                container,
                false);
        View v = fragmentExploreRecommendedrouteBinding.getRoot();

        this.bindData().bindView().bindEvent();
        return v;
    }

    @Override
    public ExploreRecommendRoute bindView() {
        return this;
    }

    @Override
    public ExploreRecommendRoute bindData() {
        return this;
    }

    @Override
    public ExploreRecommendRoute bindEvent() {
        fragmentExploreRecommendedrouteBinding.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fragmentExploreRecommendedrouteBinding.getRoot().getContext(), RouteCommendInfoActivity.class);
                startActivity(intent);
            }
        });
        return this;
    }
}
