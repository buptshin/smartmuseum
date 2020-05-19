package com.example.smartmuseum.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class SearchRecordModel extends ViewModel {

    private MutableLiveData<List<SearchRecord>> records;

    //记录条数
    private MutableLiveData<Integer> count;

    public SearchRecordModel(){
        if (records == null){
            records = new MutableLiveData<List<SearchRecord>> ();
        }
        if (count == null){
            count = new MutableLiveData<>();
            count.setValue(0);
        }
    }

    public MutableLiveData<List<SearchRecord>>  getRecords() {
        if (records == null){
            records = new MutableLiveData<List<SearchRecord>>() ;
        }
        return records;
    }

    public void setRecords(List<SearchRecord> records) {
        this.records.setValue(records);
        count.setValue(records.size());
    }

    //获取第一个搜索记录
    public SearchRecord getFistRecord(){
        if (records.getValue().size() != 0){
            return records.getValue().get(0);
        }
        return null;
    }

    public MutableLiveData<Integer> getCount() {
        return count;
    }

    //清除历史记录
    public void clearRecords(){
        records.getValue().clear();
        count.setValue(0);
    }
}
