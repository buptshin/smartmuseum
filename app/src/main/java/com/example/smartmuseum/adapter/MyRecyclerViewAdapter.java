package com.example.smartmuseum.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ExhibitionItemBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.Exhibition;

import java.util.ArrayList;
import java.util.List;

/*
展览模块RecyclerView Adapter
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Exhibition> exhibition_list;

    public MyRecyclerViewAdapter(ArrayList<Exhibition> exhibition_list) {
        this.exhibition_list = exhibition_list;
    }

    /*
    ViewHolder内部静态类
     */
    static public class ViewHolder extends RecyclerView.ViewHolder implements ViewChainedBinding {

        ExhibitionItemBinding rvBinding;
        //        // 创建item layout各个组件的view
        private View exhibition_item_view;
//        private ImageView exhibition_imageView;
//        private TextView exhibition_textView;
//        private TextView exhibition_state;
//        private TextView exhibition_passenger_flow;
//        private ImageView exhibition_like;
//        private TextView exhibition_hall;
//        private TextView exhibition_time;
//        private TextView exhibition_distance;

        public ViewHolder(View view) {
            super(view);
            exhibition_item_view = view;
            rvBinding = DataBindingUtil.bind(exhibition_item_view);
//            // 将各个view交给ViewHolder
//
//            exhibition_imageView = view.findViewById(R.id.exhibition_image);
//            exhibition_textView = view.findViewById(R.id.exhibition_name);
//            exhibition_state = view.findViewById(R.id.exhibition_state);
//            exhibition_passenger_flow = view.findViewById(R.id.exhibition_passenger_flow);
//            exhibition_like = view.findViewById(R.id.exhibition_like);
//            exhibition_hall = view.findViewById(R.id.exhibition_hall);
//            exhibition_time = view.findViewById(R.id.exhibition_time);
//            exhibition_distance = view.findViewById(R.id.exhibition_distance);
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
    }

    @Nullable
    @Override
    public ViewHolder onCreateViewHolder(@Nullable ViewGroup parent, int viewType) {
        /**
         * 为每个item inflater出view，返回ViewHolder
         */
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exhibition_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        // 设置点击事件
        holder.exhibition_item_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TO DO：recyclerview点击事件
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /**
         * 适配渲染数据到View上,该操作是对于RecyclerViewList的每一个item分别执行
         */
        Exhibition exhibition = exhibition_list.get(position);  // 获取每个位置的exhibition item的对象

        // 渲染数据到View上，数据获取方式：使用在MainActivity传入的list中exhibition对象的get方法
        holder.rvBinding.exhibitionImage.setImageResource(exhibition.getExhibition_id());
        holder.rvBinding.exhibitionName.setText(exhibition.getExhibition_name());
        holder.rvBinding.exhibitionState.setText(exhibition.getExhibition_state());
        holder.rvBinding.exhibitionPassengerFlow.setText(String.valueOf(exhibition.getExhibition_passenger_flow()) + "人次浏览过");
        holder.rvBinding.exhibitionLike.setImageResource(exhibition.getExhibition_like());
        holder.rvBinding.exhibitionHall.setText(exhibition.getExhibition_hall());
        holder.rvBinding.exhibitionTime.setText("平均停留时间为：" + exhibition.getExhibition_time() + "小时");
        holder.rvBinding.exhibitionDistance.setText(exhibition.getExhibition_distance() + " 米");


//        holder.exhibition_imageView.setImageResource(exhibition.getExhibition_id());
//        holder.exhibition_textView.setText(exhibition.getExhibition_name());
//        holder.exhibition_state.setText(exhibition.getExhibition_state());
//        holder.exhibition_passenger_flow.setText(String.valueOf(exhibition.getExhibition_passenger_flow())+"人次浏览过");
//        holder.exhibition_like.setImageResource(exhibition.getExhibition_like());
//        holder.exhibition_hall.setText(exhibition.getExhibition_hall());
//        holder.exhibition_time.setText("平均停留时间为："+exhibition.getExhibition_time()+"小时");
//        holder.exhibition_distance.setText(exhibition.getExhibition_distance()+" 米");
    }

    @Override
    public int getItemCount() {
        return exhibition_list.size();
    }


}
