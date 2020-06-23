package com.example.smartmuseum.view.me.friend;


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
import com.example.smartmuseum.view.otherview.NoScrollViewPager;
import com.example.smartmuseum.viewmodel.AccompanyCountViewModel;
import com.example.smartmuseum.viewmodel.AccompanyViewModel;

import java.util.HashMap;

public class FriendChooseFragment extends Fragment implements ViewChainedBinding {

    private FragmentChooseFriendsBinding fragmentChooseFriendBinding;
    private AccompanyViewModel accompanyViewModel;
    private AccompanyCountViewModel accompanyCountViewModel;

    public static FriendChooseFragment getInstance(){
        FriendChooseFragment fragment = new FriendChooseFragment();
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
    public FriendChooseFragment bindView() {
//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        fragmentChooseFriendBinding.friendChooseFriendsAccompanyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        HashMap hashMap=new HashMap();
        fragmentChooseFriendBinding.friendChooseFriendsAccompanyRecyclerView.setAdapter(new FriendFragmentAccompanyAdapter(accompanyViewModel.getAccompanyList(hashMap).getValue(),accompanyCountViewModel));
        return this;
    }

    @Override
    public FriendChooseFragment bindData() {
        accompanyViewModel = new ViewModelProvider(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),this)).get(AccompanyViewModel.class);
        accompanyCountViewModel = new ViewModelProvider(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),this)).get(AccompanyCountViewModel.class);
        fragmentChooseFriendBinding.setData(accompanyCountViewModel);
        return this;
    }

    @Override
    public FriendChooseFragment bindEvent() {
        fragmentChooseFriendBinding.friendChooseFriendsBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parent = fragmentChooseFriendBinding.getRoot().getRootView();
                NoScrollViewPager noScrollViewPager = (NoScrollViewPager)parent.findViewById(R.id.mainpage_myinfo_sv);
                noScrollViewPager.setCurrentItem(1,false);
            }
        });

        fragmentChooseFriendBinding.friendChooseFriendsStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击“立即开始按钮后，跳转到主页”
            }
        });
        return this;
    }
}
