package com.example.smartmuseum.adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;


import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.GoodsRecommendItemBinding;
import com.example.smartmuseum.model.Accompany;
import com.example.smartmuseum.model.Goods;

import java.util.List;

public class MainPageGoodsCommendAdapter extends RecyclerView.Adapter<MainPageGoodsCommendAdapter.GoodsViewHolder> {

    private List<Goods> mList;

    public MainPageGoodsCommendAdapter(List<Goods> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public GoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GoodsRecommendItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.goods_recommend_item,parent,false);
        return new GoodsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsViewHolder holder, int position) {
        Goods goods = mList.get(position);
        holder.getBinding().setGoods(goods);
        holder.getBinding().mainpageGoodsSellCommendGoodsOldpriceText.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class GoodsViewHolder extends RecyclerView.ViewHolder{

        GoodsRecommendItemBinding goodsRecommendItemBinding;
        public GoodsViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.goodsRecommendItemBinding = (GoodsRecommendItemBinding)binding;
        }
        public GoodsRecommendItemBinding getBinding(){
            return goodsRecommendItemBinding;
        }
    }
}
