package com.example.smartmuseum.view.startpage;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentStartPageTwoBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartPageTwoFragment extends Fragment implements ViewChainedBinding {

    private FragmentStartPageTwoBinding mBinding;


    public StartPageTwoFragment() {
        // Required empty public constructor
    }

    public static StartPageTwoFragment getInstance(){
        return new StartPageTwoFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_start_page_two, container, false);
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_start_page_two,container,false);
        this.bindData().bindView().bindEvent();
        return mBinding.getRoot();
    }

    @Override
    public StartPageTwoFragment bindView() {
        return this;
    }

    @Override
    public StartPageTwoFragment bindData() {
        return this;
    }

    @Override
    public StartPageTwoFragment bindEvent() {
        return this;
    }
}
