package com.example.smartmuseum.view.startpage;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentStartPageOneBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartPageOneFragment extends Fragment implements ViewChainedBinding {

    private FragmentStartPageOneBinding mBinding;
    public StartPageOneFragment() {
        // Required empty public constructor
    }

    public static StartPageOneFragment getInstance(){
        StartPageOneFragment fragment = new StartPageOneFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_start_page_one, container, false);
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_start_page_one,
                container,
                false);
        this.bindData().bindView().bindEvent();
        return mBinding.getRoot();
    }

    @Override
    public StartPageOneFragment bindView() {
        return this;
    }

    @Override
    public StartPageOneFragment bindData() {
        return this;
    }

    @Override
    public StartPageOneFragment bindEvent() {
        return this;
    }
}
