package com.example.smartmuseum.view.friend;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentMyFriendsBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;

public class MyFriendsFragment extends Fragment implements ViewChainedBinding<MyFriendsFragment> {

    private FragmentMyFriendsBinding fragmentMyFriendsBinding;

    public static MyFriendsFragment getInstance(){
        MyFriendsFragment fragment = new MyFriendsFragment();
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
    public MyFriendsFragment bindView() {
        return this;
    }

    @Override
    public MyFriendsFragment bindData() {
        return this;
    }

    @Override
    public MyFriendsFragment bindEvent() {
        return null;
    }
}
