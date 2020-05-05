package com.example.smartmuseum.view.friend;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentChooseFriendBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;

public class ChooseFriendsFragment extends Fragment implements ViewChainedBinding {

    private FragmentChooseFriendBinding fragmentChooseFriendBinding;

    public static ChooseFriendsFragment getInstance(){
        ChooseFriendsFragment fragment = new ChooseFriendsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentChooseFriendBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_choose_friend,
                container,
                false);
        View v = fragmentChooseFriendBinding.getRoot();
        this.bindData().bindView().bindEvent();
        return v;
    }

    @Override
    public ChooseFriendsFragment bindView() {
        return this;
    }

    @Override
    public ChooseFriendsFragment bindData() {
        return this;
    }

    @Override
    public ChooseFriendsFragment bindEvent() {
        return null;
    }
}
