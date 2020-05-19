package com.example.smartmuseum.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmuseum.R;
import com.example.smartmuseum.view.mainpage.MainActivity;

import java.util.ArrayList;
import java.util.List;


public class MainPageBookServiceListAdapter extends BaseAdapter {
    List<String> data = new ArrayList<>();
    private Context context;
    ViewHolder holder;
    private SparseBooleanArray stateCheckedMap = new SparseBooleanArray();  // 用来存放checkbox的选中状态

    public MainPageBookServiceListAdapter(Context context, List<String> data, SparseBooleanArray stateCheckedMap) {
        this.data = data;
        this.context = context;
        this.stateCheckedMap = stateCheckedMap;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.fragment_explore_bookvisit_servicelist_item, null);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.checkBox = convertView.findViewById(R.id.check_button);
        holder.mData = convertView.findViewById(R.id.service_name);
        holder.mData.setText(data.get(i));
        holder.checkBox.setChecked(stateCheckedMap.get(i));
        holder.checkBox.setVisibility(View.VISIBLE);  // 设置checkbox的框不出现
        return convertView;
    }


    public class ViewHolder {
        public TextView mData;
        public AppCompatCheckBox checkBox;
    }

    public void notifyDataSetChanged() {
        // 可以写个空函数嘛？
    }
}
