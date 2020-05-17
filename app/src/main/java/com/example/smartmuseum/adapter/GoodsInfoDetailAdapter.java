package com.example.smartmuseum.adapter;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.GoodsInfoDetailDetailItemBinding;
import com.example.smartmuseum.model.Goods;
import com.example.smartmuseum.model.GoodsFeature;

import java.util.List;

public class GoodsInfoDetailAdapter extends RecyclerView.Adapter<GoodsInfoDetailAdapter.GoodsViewHolder>{
    private List<GoodsFeature> mList;

    public GoodsInfoDetailAdapter(List<GoodsFeature> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public GoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GoodsInfoDetailDetailItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.goods_info_detail_detail_item,parent,false);
        return new GoodsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsViewHolder holder, int position) {
        GoodsFeature goodsFeature = mList.get(position);
        holder.getBinding().setGoodsfeature(goodsFeature);


        Resources res = holder.getBinding().getRoot().getResources();
        int resourceFlag = position % 3;
        Bitmap goodsImgBmp;
        if (resourceFlag == 0) {
            goodsImgBmp = BitmapFactory.decodeResource(res, R.drawable.goods_info_detail_goods_1);
            holder.getBinding().goodsInfoDetailItemImage.setImageBitmap(goodsImgBmp);
        } else if (resourceFlag == 1) {
            goodsImgBmp = BitmapFactory.decodeResource(res, R.drawable.goods_info_detail_goods_2);
            holder.getBinding().goodsInfoDetailItemImage.setImageBitmap(goodsImgBmp);
        } else {
            goodsImgBmp = BitmapFactory.decodeResource(res, R.drawable.goods_info_detail_goods_3);
            holder.getBinding().goodsInfoDetailItemImage.setImageBitmap(goodsImgBmp);
        }

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class GoodsViewHolder extends RecyclerView.ViewHolder{

        GoodsInfoDetailDetailItemBinding goodsInfoDetailDetailItemBinding;
        public GoodsViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.goodsInfoDetailDetailItemBinding = (GoodsInfoDetailDetailItemBinding)binding;
        }
        public GoodsInfoDetailDetailItemBinding getBinding(){
            return goodsInfoDetailDetailItemBinding;
        }
    }
}
