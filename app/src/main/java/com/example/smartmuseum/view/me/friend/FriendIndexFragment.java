package com.example.smartmuseum.view.me.friend;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentMyFriendsBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.view.otherview.NoScrollViewPager;

public class FriendIndexFragment extends Fragment implements ViewChainedBinding<FriendIndexFragment> {

    private FragmentMyFriendsBinding fragmentMyFriendsBinding;
    private NoScrollViewPager noScrollViewPager;

    public static FriendIndexFragment getInstance(){
        FriendIndexFragment fragment = new FriendIndexFragment();
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMyFriendsBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_my_friends,
                container,
                false);
        View v = fragmentMyFriendsBinding.getRoot();
        this.bindData().bindView().bindEvent();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View parent = fragmentMyFriendsBinding.getRoot().getRootView();
        noScrollViewPager = (NoScrollViewPager)parent.findViewById(R.id.mainpage_myinfo_sv);
    }

    @Override
    public FriendIndexFragment bindView() {
        return this;
    }

    @Override
    public FriendIndexFragment bindData() {
        return this;
    }

    @Override
    public FriendIndexFragment bindEvent() {
        fragmentMyFriendsBinding.friendMyFirendsPersonalStartTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noScrollViewPager.setCurrentItem(2,false);
            }
        });
        fragmentMyFriendsBinding.friendMyFirendsCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noScrollViewPager.setCurrentItem(0);
            }
        });
        return this;
    }
}
