package com.example.smartmuseum.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.NavigationSearchRecordItemBinding;
import com.example.smartmuseum.model.SearchRecord;

import java.util.List;

public class NavigationSearchRecordAdapter extends RecyclerView.Adapter<NavigationSearchRecordAdapter.RecordViewHolder>{
    private List<SearchRecord> mList;

    public NavigationSearchRecordAdapter(List<SearchRecord> searchRecordList) {
        this.mList = searchRecordList;
    }

    public NavigationSearchRecordAdapter() {

    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NavigationSearchRecordItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.navigation_search_record_item, parent,false);
        return new RecordViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        SearchRecord record = mList.get(position);
        holder.getBinding().setRecord(record);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class RecordViewHolder extends RecyclerView.ViewHolder{

        NavigationSearchRecordItemBinding searchRecordItemBinding;
        public RecordViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.searchRecordItemBinding = (NavigationSearchRecordItemBinding)binding;
        }
        public NavigationSearchRecordItemBinding getBinding(){
            return searchRecordItemBinding;
        }
    }
}
