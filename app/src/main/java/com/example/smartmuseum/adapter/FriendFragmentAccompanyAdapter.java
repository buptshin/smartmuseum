package com.example.smartmuseum.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FriendItemAccompanyBinding;
import com.example.smartmuseum.model.Accompany;
import com.example.smartmuseum.view.GlobalVariables;
import com.example.smartmuseum.view.otherview.NoScrollViewPager;

import java.util.List;

public class FriendFragmentAccompanyAdapter extends RecyclerView.Adapter<FriendFragmentAccompanyAdapter.AccompanyViewHolder> {

    private List<Accompany> mList;

    public FriendFragmentAccompanyAdapter(List<Accompany> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public AccompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FriendItemAccompanyBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.friend_item_accompany,parent,false);
        return new AccompanyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AccompanyViewHolder holder, int position) {
        Accompany accompany = mList.get(position);
        holder.getBinding().setAccompany(accompany);
        holder.getBinding().friendChooseFriendsItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 如果当前item下的同伴正在博物馆且没有添加，就可以进行添加的操作
                if(!accompany.getAdded()&&accompany.getInMuseum()){
                    accompany.setAdded(true);
                    // 跳转到MainActivity的“探索”界面，并默认打开侧滑栏
                    GlobalVariables.hasAcompany = true;
                    View parent = holder.getBinding().getRoot().getRootView();
                    NoScrollViewPager noScrollViewPager = (NoScrollViewPager)parent.findViewById(R.id.mainpage_noscrollviewpager);
                    noScrollViewPager.setCurrentItem(0,false);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class AccompanyViewHolder extends RecyclerView.ViewHolder{

        FriendItemAccompanyBinding friendItemAccompanyBinding;
        public AccompanyViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.friendItemAccompanyBinding = (FriendItemAccompanyBinding)binding;
        }
        public FriendItemAccompanyBinding getBinding(){
            return friendItemAccompanyBinding;
        }
    }
}
