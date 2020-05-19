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
import com.example.smartmuseum.view.otherview.NoScrollViewPager;
import com.example.smartmuseum.view.settings.SettingsActivity;

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
        mBinding.mainpageMyinfoFriendsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               View parent = mBinding.getRoot().getRootView();
                NoScrollViewPager noScrollViewPager = (NoScrollViewPager)parent.findViewById(R.id.mainpage_noscrollviewpager);
                noScrollViewPager.setCurrentItem(4,false);
            }
        });
        mBinding.mainpageMyinfoSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mBinding.getRoot().getContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });
        return this;
    }
}
