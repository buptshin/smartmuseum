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
import com.example.smartmuseum.databinding.GoodsOrderCheckItemBinding;
import com.example.smartmuseum.model.Goods;

import java.util.List;

public class GoodsOrderCheckAdapter extends RecyclerView.Adapter<GoodsOrderCheckAdapter.GoodsViewHolder> {

    private List<Goods> mList;

    public GoodsOrderCheckAdapter(List<Goods> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public GoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GoodsOrderCheckItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.goods_order_check_item,parent,false);
        return new GoodsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsViewHolder holder, int position) {
        Goods goods = mList.get(position);
        holder.getBinding().setGoods(goods);

        Resources res = holder.getBinding().getRoot().getResources();
        Bitmap bmp;
        int resourceFlag = position % 3;
        if (resourceFlag == 0) {
            bmp = BitmapFactory.decodeResource(res, R.drawable.goods_order_check_goods_1);
        } else if (resourceFlag == 1){
            bmp = BitmapFactory.decodeResource(res, R.drawable.goods_order_check_goods_2);
        } else {
            bmp = BitmapFactory.decodeResource(res, R.drawable.goods_order_check_goods_3);
        }

        holder.getBinding().goodsOrderCheckItemGoodsImg.setImageBitmap(bmp);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class GoodsViewHolder extends RecyclerView.ViewHolder{

        GoodsOrderCheckItemBinding goodsOrderCheckItemBinding;
        public GoodsViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.goodsOrderCheckItemBinding = (GoodsOrderCheckItemBinding)binding;
        }
        public GoodsOrderCheckItemBinding getBinding(){
            return goodsOrderCheckItemBinding;
        }
    }
}