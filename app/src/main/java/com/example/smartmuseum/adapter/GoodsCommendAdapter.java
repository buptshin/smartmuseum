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
import com.example.smartmuseum.databinding.GoodsCommendItemBinding;
import com.example.smartmuseum.model.Goods;

import java.util.List;

public class GoodsCommendAdapter extends RecyclerView.Adapter<GoodsCommendAdapter.GoodsViewHolder> {

    private List<Goods> mList;

    public GoodsCommendAdapter(List<Goods> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public GoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GoodsCommendItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.goods_commend_item,parent,false);
        return new GoodsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsViewHolder holder, int position) {
        Goods goods = mList.get(position);
        holder.getBinding().setGoods(goods);

        Resources res = holder.getBinding().getRoot().getResources();
        Bitmap bmp;
        int resourceFlag = position % 4;
        if (resourceFlag == 0) {
            bmp = BitmapFactory.decodeResource(res, R.drawable.route_commend_popupwindow_goods_1);
        } else if (resourceFlag == 1) {
            bmp = BitmapFactory.decodeResource(res, R.drawable.route_commend_popupwindow_goods_2);
        } else if (resourceFlag == 2) {
            bmp = BitmapFactory.decodeResource(res, R.drawable.route_commend_popupwindow_goods_3);
        } else {
            bmp = BitmapFactory.decodeResource(res, R.drawable.route_commend_popupwindow_goods_4);
        }
        holder.getBinding().goodsCommendItemImg.setImageBitmap(bmp);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class GoodsViewHolder extends RecyclerView.ViewHolder{

        GoodsCommendItemBinding goodsCommendItemBinding;
        public GoodsViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.goodsCommendItemBinding = (GoodsCommendItemBinding)binding;
        }
        public GoodsCommendItemBinding getBinding(){
            return goodsCommendItemBinding;
        }
    }

}