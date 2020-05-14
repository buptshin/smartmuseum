package com.example.smartmuseum.adapter;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.GoodsMarketDiscountItemBinding;
import com.example.smartmuseum.model.Goods;

import java.util.List;

public class GoodsMarketDiscountAdapter extends RecyclerView.Adapter<GoodsMarketDiscountAdapter.GoodsViewHolder> {

    private List<Goods> mList;

    public GoodsMarketDiscountAdapter(List<Goods> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public GoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GoodsMarketDiscountItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.goods_market_discount_item,parent,false);
        return new GoodsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsViewHolder holder, int position) {
        Goods goods = mList.get(position);
        holder.getBinding().setGoods(goods);
        long time;

        Resources res = holder.getBinding().getRoot().getResources();
        Bitmap bmp;
        int resourceFlag = position % 2;
        if (resourceFlag == 0) {
            bmp = BitmapFactory.decodeResource(res, R.drawable.goods_market_discount_goods_1);
            holder.getBinding().goodsMarketGoodsNumberView.setScales(new double[]{0.8, 0.2});
            time = 10000000;
        } else {
            bmp = BitmapFactory.decodeResource(res, R.drawable.goods_market_discount_goods_2);
            holder.getBinding().goodsMarketGoodsNumberView.setScales(new double[]{0.9, 0.1});
            time = 8000000;
        }

        CountDownTimer countDownTimer = new CountDownTimer(time, 1000){
            @Override
            public void onTick(long millisUntilFinished) {

                //单位天
                long day = millisUntilFinished / (1000 * 24 * 60 * 60); //单位天
                //单位时
                long hour = (millisUntilFinished - day * (1000 * 24 * 60 * 60)) / (1000 * 60 * 60);
                //单位分
                long minute = (millisUntilFinished - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60)) / (1000 * 60);
                //单位秒
                long second = (millisUntilFinished - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60) - minute * (1000 * 60)) / 1000;

                holder.getBinding().goodsMarketGoodsDiscountReemainderTimeText.setText(hour + ":"+ minute + ":" + second);
            }

            @Override
            public void onFinish() {
            }
        };
        countDownTimer.start();

        holder.getBinding().goodsMarketDiscountGoodsImage.setImageBitmap(bmp);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class GoodsViewHolder extends RecyclerView.ViewHolder{

        GoodsMarketDiscountItemBinding goodsMarketDiscountItemBinding;
        public GoodsViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.goodsMarketDiscountItemBinding = (GoodsMarketDiscountItemBinding)binding;
        }
        public GoodsMarketDiscountItemBinding getBinding(){
            return goodsMarketDiscountItemBinding;
        }
    }

}