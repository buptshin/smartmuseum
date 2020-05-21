package com.example.smartmuseum.view.friend;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.FriendFragmentAccompanyAdapter;
import com.example.smartmuseum.databinding.FragmentChooseFriendsBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.view.otherview.NoScrollViewPager;
import com.example.smartmuseum.viewmodel.AccompanyViewModel;

import java.util.HashMap;

public class ChooseFriendsFragment extends Fragment implements ViewChainedBinding {

    private FragmentChooseFriendsBinding fragmentChooseFriendBinding;
    private AccompanyViewModel accompanyViewModel;

    public static ChooseFriendsFragment getInstance(){
        ChooseFriendsFragment fragment = new ChooseFriendsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentChooseFriendBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_choose_friends,
                container,
                false);
        View v = fragmentChooseFriendBinding.getRoot();

        this.bindData().bindView().bindEvent();
        return v;
    }

    @Override
    public ChooseFriendsFragment bindView() {
//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        fragmentChooseFriendBinding.friendChooseFriendsAccompanyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        HashMap hashMap=new HashMap();
        fragmentChooseFriendBinding.friendChooseFriendsAccompanyRecyclerView.setAdapter(new FriendFragmentAccompanyAdapter(accompanyViewModel.getAccompanyList(hashMap).getValue()));
        return this;
    }

    @Override
    public ChooseFriendsFragment bindData() {
        accompanyViewModel = new ViewModelProvider(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),this)).get(AccompanyViewModel.class);
        return this;
    }

    @Override
    public ChooseFriendsFragment bindEvent() {
        fragmentChooseFriendBinding.friendChooseFriendsBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parent = fragmentChooseFriendBinding.getRoot().getRootView();
                NoScrollViewPager noScrollViewPager = (NoScrollViewPager)parent.findViewById(R.id.mainpage_noscrollviewpager);
                noScrollViewPager.setCurrentItem(4,false);
            }
        });
        return this;
    }
}
