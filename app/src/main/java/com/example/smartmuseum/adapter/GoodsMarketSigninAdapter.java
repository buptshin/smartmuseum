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
import com.example.smartmuseum.databinding.GoodsMarketDiscountItemBinding;
import com.example.smartmuseum.databinding.GoodsMarketSigninItemBinding;
import com.example.smartmuseum.model.Goods;
import com.example.smartmuseum.model.GoodsCollectionModel;

import java.util.List;

public class GoodsMarketSigninAdapter extends RecyclerView.Adapter<GoodsMarketSigninAdapter.GoodsCollectionViewHolder> {

    private List<GoodsCollectionModel> mList;

    public GoodsMarketSigninAdapter(List<GoodsCollectionModel> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public GoodsCollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GoodsMarketSigninItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.goods_market_signin_item,parent,false);
        return new GoodsCollectionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsCollectionViewHolder holder, int position) {
        GoodsCollectionModel goodsCollectionModel = mList.get(position);
        holder.getBinding().setGoodsCollectionModel(goodsCollectionModel);

        Resources res = holder.getBinding().getRoot().getResources();
        Bitmap collectionBmp, goodsBmp;
        int resourceFlag = position % 2;
        if (resourceFlag == 0) {
            collectionBmp = BitmapFactory.decodeResource(res, R.drawable.goods_market_signin_collection_3);
            goodsBmp = BitmapFactory.decodeResource(res, R.drawable.goods_market_signin_goods_3);
        } else {
            collectionBmp = BitmapFactory.decodeResource(res, R.drawable.goods_market_signin_collection_4);
            goodsBmp = BitmapFactory.decodeResource(res, R.drawable.goods_market_signin_goods_4);
        }

        holder.getBinding().goodsMarketSigninItemCollcetionInclude.goodsMarketSigninCollcetionImg.setImageBitmap(collectionBmp);
        holder.getBinding().goodsMarketSigninItemGoodsInclude.goodsMarketSigninGoodsImg.setImageBitmap(goodsBmp);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class GoodsCollectionViewHolder extends RecyclerView.ViewHolder{

        GoodsMarketSigninItemBinding goodsMarketDiscountItemBinding;
        public GoodsCollectionViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.goodsMarketDiscountItemBinding = (GoodsMarketSigninItemBinding)binding;
        }
        public GoodsMarketSigninItemBinding getBinding(){
            return goodsMarketDiscountItemBinding;
        }
    }

}
