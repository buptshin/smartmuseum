package com.example.smartmuseum.adapter;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.GoodsMarketClassicalItemBinding;
import com.example.smartmuseum.model.Goods;
import com.example.smartmuseum.view.goods.GoodsInfoActivity;
import com.example.smartmuseum.view.goods.GoodsRecommendActivity;
import com.example.smartmuseum.view.mainpage.MainActivity;

import java.util.List;

public class GoodsMarketClassicalAdapter extends RecyclerView.Adapter<GoodsMarketClassicalAdapter.GoodsViewHolder> {

    private List<Goods> mList;

    public GoodsMarketClassicalAdapter(List<Goods> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public GoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GoodsMarketClassicalItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.goods_market_classical_item,parent,false);
        return new GoodsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsViewHolder holder, int position) {
        Goods goods = mList.get(position);
        holder.getBinding().setGoods(goods);
        Resources res = holder.getBinding().getRoot().getResources();
        int resourceFlag = position % 3;
        Bitmap goodsImgBmp;
        if (resourceFlag == 0) {
            goodsImgBmp = BitmapFactory.decodeResource(res, R.drawable.goods_market_classical_goods_1);
            holder.getBinding().goodsMarketClassicalGoodsImage.setImageBitmap(goodsImgBmp);
            holder.getBinding().goodsMarketClassicalBackgroundImg.setImageResource(R.drawable.goods_market_classical_card_background_1);
        } else if (resourceFlag == 1) {
            goodsImgBmp = BitmapFactory.decodeResource(res, R.drawable.goods_market_classical_goods_2);
            holder.getBinding().goodsMarketClassicalGoodsImage.setImageBitmap(goodsImgBmp);
            holder.getBinding().goodsMarketClassicalBackgroundImg.setImageResource(R.drawable.goods_market_classical_card_background_2);
        } else {
            goodsImgBmp = BitmapFactory.decodeResource(res, R.drawable.goods_market_classical_goods_1);
            holder.getBinding().goodsMarketClassicalGoodsImage.setImageBitmap(goodsImgBmp);
            holder.getBinding().goodsMarketClassicalBackgroundImg.setImageResource(R.drawable.goods_market_classical_card_background_3);
        }


        holder.getBinding().goodsMarketClassicalBackgroundImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.getBinding().getRoot().getContext(), GoodsInfoActivity.class);
                holder.getBinding().getRoot().getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class GoodsViewHolder extends RecyclerView.ViewHolder{

        GoodsMarketClassicalItemBinding goodsMarketClassicalItemBinding;
        public GoodsViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.goodsMarketClassicalItemBinding = (GoodsMarketClassicalItemBinding)binding;
        }
        public GoodsMarketClassicalItemBinding getBinding(){
            return goodsMarketClassicalItemBinding;
        }
    }

}