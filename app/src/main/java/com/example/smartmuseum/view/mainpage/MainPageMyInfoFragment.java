package com.example.smartmuseum.view.mainpage;

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
import com.example.smartmuseum.databinding.FragmentMainpageMyinfoBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.view.me.FieldGuideActivity;
import com.example.smartmuseum.view.me.MuseumInfoActivity;

public class MainPageMyInfoFragment extends Fragment implements ViewChainedBinding {

    private FragmentMainpageMyinfoBinding mBinding;


    public static MainPageMyInfoFragment getInstance() {
        MainPageMyInfoFragment fragment = new MainPageMyInfoFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_mainpage_myinfo,
                container,
                false);
        View v = mBinding.getRoot();

        this.bindData().bindView().bindEvent();
        return v;
    }

    @Override
    public MainPageMyInfoFragment bindData() {
        return this;
    }

    @Override
    public MainPageMyInfoFragment bindView() {
        return this;
    }

    @Override
    public MainPageMyInfoFragment bindEvent() {
        mBinding.infobutton.setOnClickListener(view -> {
            Intent intent = new Intent(mBinding.getRoot().getContext(), MuseumInfoActivity.class);
            startActivity(intent);
        });
        mBinding.fgButton.setOnClickListener(view -> {
            Intent intent = new Intent(mBinding.getRoot().getContext(), FieldGuideActivity.class);
            startActivity(intent);
        });

        return this;
    }
}
