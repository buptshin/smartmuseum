package com.example.smartmuseum.adapter;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.GoodsPurchasedItemBinding;
import com.example.smartmuseum.model.Goods;
import com.example.smartmuseum.util.GlideRoundTransform;
import com.example.smartmuseum.view.goods.GoodsOrderStatusActivity;

import java.util.List;

public class MainPageGoodsPurchasedAdapter extends RecyclerView.Adapter<MainPageGoodsPurchasedAdapter.GoodsViewHolder> {

    private List<Goods> mList;

    public MainPageGoodsPurchasedAdapter(List<Goods> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public GoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GoodsPurchasedItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.goods_purchased_item,parent,false);
        return new GoodsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsViewHolder holder, int position) {
        Goods goods = mList.get(position);
        holder.getBinding().setGoods(goods);
        //为文本设置中划线
        holder.getBinding().goodsPurchasedGoodsOldpriceText.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

//        Resources res = holder.getBinding().getRoot().getResources();
//        Bitmap bmp;
//        int resourceFlag = position % 2;
//        if (resourceFlag == 0) {
//            bmp = BitmapFactory.decodeResource(res, R.drawable.mainpage_goods_sell_goods_1);
//        } else {
//            bmp = BitmapFactory.decodeResource(res, R.drawable.mainpage_goods_sell_goods_2);
//        }
//
//        holder.getBinding().goodsPurchasedGoodsImage.setImageBitmap(bmp);

        //圆角和centerCrop冲突
        int resourceFlag = position % 2;
        RequestOptions myOptions = new RequestOptions()
                .transform(new GlideRoundTransform(holder.getBinding().getRoot().getContext()));
        if (resourceFlag == 0) {
            Glide.with(holder.getBinding().getRoot())
                    .load(R.drawable.mainpage_goods_sell_goods_1)
                    .apply(myOptions)
                    .into(holder.getBinding().goodsPurchasedGoodsImage);
        } else {
            Glide.with(holder.getBinding().getRoot())
                    .load(R.drawable.mainpage_goods_sell_goods_2)
                    .apply(myOptions)
                    .into(holder.getBinding().goodsPurchasedGoodsImage);
        }

        holder.getBinding().goodsPurchasedGoodsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.getBinding().getRoot().getContext(), GoodsOrderStatusActivity.class);
                holder.getBinding().getRoot().getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class GoodsViewHolder extends RecyclerView.ViewHolder{

        GoodsPurchasedItemBinding goodsPurchasedItemBinding;
        public GoodsViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.goodsPurchasedItemBinding = (GoodsPurchasedItemBinding)binding;
        }
        public GoodsPurchasedItemBinding getBinding(){
            return goodsPurchasedItemBinding;
        }
    }
}
