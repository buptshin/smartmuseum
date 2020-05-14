package com.example.smartmuseum.view.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentExploreRecommendedrouteBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;

public class ExploreRecommendRoute extends Fragment implements ViewChainedBinding<ExploreRecommendRoute> {
    private FragmentExploreRecommendedrouteBinding fragmentExploreRecommendedrouteBinding;

    public static ExploreRecommendRoute getInstance() {
        ExploreRecommendRoute fragment = new ExploreRecommendRoute();
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {
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
        return null;
    }

    @Override
    public ExploreRecommendRoute bindData() {
        return null;
    }

    @Override
    public ExploreRecommendRoute bindEvent() {
        return null;
    }


}
