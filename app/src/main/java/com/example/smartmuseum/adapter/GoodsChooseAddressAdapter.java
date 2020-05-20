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
    private int lastClickPosition = -1;

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

        if (position == 0) {
            holder.getBinding().goodsChooseAddressItemDefaultFlag.setVisibility(View.VISIBLE);
        }

        Resources res = holder.getBinding().getRoot().getResources();
        Bitmap chooseImgBmp;
        if (address.isDefaultFlag()) {
            chooseImgBmp = BitmapFactory.decodeResource(res, R.mipmap.goods_choose_address_circle_clickable);
            holder.getBinding().goodsChooseAddressItemChooseImg.setImageBitmap(chooseImgBmp);
            lastClickPosition = position;
        } else {
            chooseImgBmp = BitmapFactory.decodeResource(res, R.mipmap.goods_choose_address_circle_unclickable);
            holder.getBinding().goodsChooseAddressItemChooseImg.setImageBitmap(chooseImgBmp);
        }



        holder.getBinding().goodsChooseAddressItemChooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newId = address.getLocationAddress();
                for (Address address : mList) {
                    String oldId = address.getLocationAddress();
                    if (newId.equals(oldId)) {
                        address.setDefaultFlag(true);
                    }else{
                        address.setDefaultFlag(false);
                    }
                }

                notifyDataSetChanged();
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
