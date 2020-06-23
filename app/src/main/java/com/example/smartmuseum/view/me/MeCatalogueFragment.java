package com.example.smartmuseum.view.me;


import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentMeCatalogueBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.view.me.settings.SettingsActivity;
import com.example.smartmuseum.view.otherview.NoScrollViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeCatalogueFragment extends Fragment implements ViewChainedBinding {

    private FragmentMeCatalogueBinding mBinding;


    public static MeCatalogueFragment getInstance(){
        return new MeCatalogueFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_me_catalogue,
                container,
                false);
        this.bindData().bindView().bindEvent();
        return mBinding.getRoot();
    }

    @Override
    public MeCatalogueFragment bindView() {
        return this;
    }

    @Override
    public MeCatalogueFragment bindData() {
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
                NoScrollViewPager noScrollViewPager = (NoScrollViewPager)parent.findViewById(R.id.mainpage_myinfo_sv);
                noScrollViewPager.setCurrentItem(1,false);
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

    @Override
    public MeCatalogueFragment bindEvent() {
        return this;
    }
}
