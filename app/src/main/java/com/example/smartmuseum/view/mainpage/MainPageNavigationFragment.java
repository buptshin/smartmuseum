package com.example.smartmuseum.view.mainpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentMainpageExploreBinding;
import com.example.smartmuseum.databinding.FragmentMainpageNavigationBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;

/*导览fragment*/
public class MainPageNavigationFragment extends Fragment implements ViewChainedBinding {

    private FragmentMainpageNavigationBinding mBinding;


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
        View v = mBinding.getRoot();

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
        return null;
    }
}
