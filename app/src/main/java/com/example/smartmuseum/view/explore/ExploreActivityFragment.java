package com.example.smartmuseum.view.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentExploreActivityBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;

public class ExploreActivityFragment extends Fragment implements ViewChainedBinding<ExploreActivityFragment> {

    private FragmentExploreActivityBinding fragmentExploreActivityBinding;

    public static ExploreActivityFragment getInstance() {
        ExploreActivityFragment fragment = new ExploreActivityFragment();
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentExploreActivityBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_explore_activity,
                container,
                false);

        View v = fragmentExploreActivityBinding.getRoot();
        this.bindData().bindView().bindEvent();

        return v;
    }

    @Override
    public ExploreActivityFragment bindView() {
        return this;
    }

    @Override
    public ExploreActivityFragment bindData() {
        return this;
    }

    @Override
    public ExploreActivityFragment bindEvent() {
        return null;
    }
}
