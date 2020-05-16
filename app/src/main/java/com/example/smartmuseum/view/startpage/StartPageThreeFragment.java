package com.example.smartmuseum.view.startpage;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentStartPageThreeBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartPageThreeFragment extends Fragment implements ViewChainedBinding {

    private FragmentStartPageThreeBinding mBinding;

    public StartPageThreeFragment() {
        // Required empty public constructor
    }

    public static StartPageThreeFragment  getInstance(){
        return new StartPageThreeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_start_page_three, container, false);
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_start_page_three,
                container,
                false);
        this.bindData().bindView().bindEvent();
        return mBinding.getRoot();
    }

    @Override
    public StartPageThreeFragment bindView() {
        return this;
    }

    @Override
    public StartPageThreeFragment bindData() {
        return this;
    }

    @Override
    public StartPageThreeFragment bindEvent() {
        return this;
    }
}
