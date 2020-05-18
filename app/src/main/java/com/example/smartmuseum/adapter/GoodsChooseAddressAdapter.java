package com.example.smartmuseum.adapter;

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
import com.example.smartmuseum.databinding.GoodsChooseAddressItemBinding;
import com.example.smartmuseum.model.Address;
import com.example.smartmuseum.model.User;

import java.util.List;

public class GoodsChooseAddressAdapter extends RecyclerView.Adapter<GoodsChooseAddressAdapter.AddressViewHolder>{
    private List<Address> mList;
    private int lastIndex = 0;

    public GoodsChooseAddressAdapter(List<Address> addressList) {
        this.mList = addressList;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GoodsChooseAddressItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.goods_choose_address_item,parent,false);
        return new AddressViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        Address address = mList.get(position);
        holder.getBinding().setAddress(address);

        Resources res = holder.getBinding().getRoot().getResources();
        Bitmap chooseImgBmp;
        if (address.isDefaultFlag()) {
            chooseImgBmp = BitmapFactory.decodeResource(res, R.mipmap.goods_choose_address_circle_clickable);
            holder.getBinding().goodsChooseAddressItemChooseImg.setImageBitmap(chooseImgBmp);
        } else {
            chooseImgBmp = BitmapFactory.decodeResource(res, R.mipmap.goods_choose_address_circle_unclickable);
            holder.getBinding().goodsChooseAddressItemChooseImg.setImageBitmap(chooseImgBmp);
        }

        holder.getBinding().goodsChooseAddressItemChooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap chooseImgBmp;
                if (address.isDefaultFlag()) {
                    mList.get(position).setDefaultFlag(false);
                    chooseImgBmp = BitmapFactory.decodeResource(res, R.mipmap.goods_choose_address_circle_unclickable);
                    holder.getBinding().goodsChooseAddressItemChooseImg.setImageBitmap(chooseImgBmp);
                } else {
                    mList.get(position).setDefaultFlag(true);
                    chooseImgBmp = BitmapFactory.decodeResource(res, R.mipmap.goods_choose_address_circle_clickable);
                    holder.getBinding().goodsChooseAddressItemChooseImg.setImageBitmap(chooseImgBmp);
                }

//                if (lastIndex != position) {
//                    AddressViewHolder test = holder;
//                    mList.get(lastIndex).setDefaultFlag(false);
//                    Address address = mList.get(lastIndex);
//                    test.getBinding().setAddress(address);
//                    chooseImgBmp = BitmapFactory.decodeResource(res, R.mipmap.goods_choose_address_circle_unclickable);
//                    test.getBinding().goodsChooseAddressItemChooseImg.setImageBitmap(chooseImgBmp);
//                    lastIndex = position;
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class AddressViewHolder extends RecyclerView.ViewHolder{

        GoodsChooseAddressItemBinding goodsOrderCheckItemBinding;
        public AddressViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.goodsOrderCheckItemBinding = (GoodsChooseAddressItemBinding)binding;
        }
        public GoodsChooseAddressItemBinding getBinding(){
            return goodsOrderCheckItemBinding;
        }
    }
}
