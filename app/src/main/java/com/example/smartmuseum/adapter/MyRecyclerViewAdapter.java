package com.example.smartmuseum.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ExhibitionItemBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.Exhibition;
import com.example.smartmuseum.view.GlobalVariables;
import com.example.smartmuseum.view.explore.ExhibitionContentActivity;
import com.example.smartmuseum.view.mainpage.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

/*
 *lzg
 *展览模块RecyclerView Adapter
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Exhibition> exhibition_list;

    GlobalVariables globalVariables = new GlobalVariables();

    // 监听器
    private OnItemClickListener onItemClickListener;

    public MyRecyclerViewAdapter(ArrayList<Exhibition> exhibition_list) {
        this.exhibition_list = exhibition_list;
    }

    /*
    ViewHolder内部静态类
     */
    static public class ViewHolder extends RecyclerView.ViewHolder implements ViewChainedBinding, OnItemClickListener {

        ExhibitionItemBinding rvBinding;
        // 创建item layout各个组件的view
        private View exhibition_item_view;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.rvBinding = (ExhibitionItemBinding) binding;
        }

        public ExhibitionItemBinding getBinding() {
            return rvBinding;
        }

        @Override
        public Object bindView() {
            return null;
        }

        @Override
        public Object bindData() {
            return null;
        }

        @Override
        public Object bindEvent() {
            return null;
        }

        @Override
        public void OnItemClick(View view) {

        }
    }

    @Nullable
    @Override
    public ViewHolder onCreateViewHolder(@Nullable ViewGroup parent, int viewType) {
        /**
         * 为每个item inflater出view，返回ViewHolder
         */
        ExhibitionItemBinding binding = DataBindingUtil.inflate((LayoutInflater.from(parent.getContext()))
                , R.layout.exhibition_item, parent, false);
        ViewHolder viewholder = new ViewHolder(binding);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /**
         * 适配渲染数据到View上,该操作是对于RecyclerViewList的每一个item分别执行
         */
        Exhibition exhibition = exhibition_list.get(position);  // 获取每个位置的exhibition item的对象

        holder.getBinding().setExhibitionInfo(exhibition);
        // 渲染数据到View上，数据获取方式：使用在MainActivity传入的list中exhibition对象的get方法
        holder.rvBinding.exhibitionImage.setImageResource(exhibition.getExhibition_id());
        holder.rvBinding.exhibitionName.setText(exhibition.getExhibition_name());
        holder.rvBinding.exhibitionState.setText(exhibition.getExhibition_state());
        holder.rvBinding.exhibitionPassengerFlow.setText(String.valueOf(exhibition.getExhibition_passenger_flow()) + "人次浏览过");
        holder.rvBinding.exhibitionLike.setImageResource(exhibition.getExhibition_like());
        holder.rvBinding.exhibitionHall.setText(exhibition.getExhibition_hall());
        holder.rvBinding.exhibitionTime.setText("平均停留时间为：" + exhibition.getExhibition_time() + "小时");
        if (globalVariables.getLocation_change() == 0) {
            holder.rvBinding.exhibitionDistance.setText(exhibition.getExhibition_distance() + " 米");
        } else {
            holder.rvBinding.exhibitionDistance.setText(exhibition.getExhibition_distance() - 150 + " 米");
        }


        holder.getBinding().exhibitionImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.getBinding().getRoot().getContext(), ExhibitionContentActivity.class);
                holder.getBinding().getRoot().getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exhibition_list.size();
    }


}
