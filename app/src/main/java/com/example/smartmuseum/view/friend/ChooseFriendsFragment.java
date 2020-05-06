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

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.FriendFragmentAccompanyAdapter;
import com.example.smartmuseum.databinding.FragmentChooseFriendsBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.viewmodel.AccompanyViewModel;

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
        fragmentChooseFriendBinding.friendChooseFriendsAccompanyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentChooseFriendBinding.friendChooseFriendsAccompanyRecyclerView.setAdapter(new FriendFragmentAccompanyAdapter(accompanyViewModel.getAccompanyList().getValue()));
        return this;
    }

    @Override
    public ChooseFriendsFragment bindData() {
        accompanyViewModel = new ViewModelProvider(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),this)).get(AccompanyViewModel.class);
        return this;
    }

    @Override
    public ChooseFriendsFragment bindEvent() {
        return null;
    }
}
