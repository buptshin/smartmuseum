package com.example.smartmuseum.view.explore;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentExploreBookvisitBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;

public class ExploreBookVisitFragment extends Fragment implements ViewChainedBinding<ExploreBookVisitFragment> {

    private FragmentExploreBookvisitBinding fragmentExploreBookvisitBinding;

    public static ExploreBookVisitFragment getInstance() {
        ExploreBookVisitFragment exploreBookVisitFragment = new ExploreBookVisitFragment();
        return exploreBookVisitFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentExploreBookvisitBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_explore_bookvisit,
                container, false
        );
        View v = fragmentExploreBookvisitBinding.getRoot();
        this.bindData().bindView().bindEvent();

        return v;
    }

    @Override
    public ExploreBookVisitFragment bindView() {
        return null;
    }

    @Override
    public ExploreBookVisitFragment bindData() {
        return null;
    }

    @Override
    public ExploreBookVisitFragment bindEvent() {
        return null;
    }
}
