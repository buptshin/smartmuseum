package com.example.smartmuseum.view.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.NavigationSearchRecordAdapter;
import com.example.smartmuseum.databinding.ActivityNavigationSearchBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.NavigationFlagModel;
import com.example.smartmuseum.model.SearchRecord;
import com.example.smartmuseum.model.SearchRecordModel;
import com.example.smartmuseum.util.ScreenUtil;
import com.example.smartmuseum.view.mainpage.MainActivity;

import java.util.ArrayList;
import java.util.List;

/*搜索activity*/
public class NavigationSearchActivity extends AppCompatActivity implements ViewChainedBinding {
    private ActivityNavigationSearchBinding mBinding;
    private SearchRecordModel recordModel;
    private List<SearchRecord> recordList;
    private NavigationSearchRecordAdapter searchRecordAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_navigation_search);
        recordModel = ViewModelProviders.of(this).get(SearchRecordModel.class);
        recordModel = new SearchRecordModel();
        mBinding.setData(recordModel);
        mBinding.setLifecycleOwner(this);

        recordModel.getCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (recordModel.getCount().getValue() == 0){
                    mBinding.navigationSearchGoTv.setVisibility(View.GONE);
                    mBinding.navigationSearchClearTv.setVisibility(View.GONE);
                    searchRecordAdapter = new NavigationSearchRecordAdapter();
                }else{
                    recordList = recordModel.getRecords().getValue();
                    mBinding.navigationSearchGoTv.setVisibility(View.VISIBLE);
                    mBinding.navigationSearchClearTv.setVisibility(View.VISIBLE);
                }
            }
        });
        mBinding.navigationSearchRecordsRecyclerview.setLayoutManager(new LinearLayoutManager(mBinding.getRoot().getContext()));
        recordModel.setRecords(initdata());
        if (recordModel.getRecords().getValue().size() != 0){
            mBinding.navigationSearchRecordsRecyclerview.setAdapter(new NavigationSearchRecordAdapter(recordModel.getRecords().getValue()));
        }
        this.bindData().bindView().bindEvent();
    }

    /*添加测试数据*/
    public List<SearchRecord> initdata(){
        List<SearchRecord> records = new ArrayList<>();
        records.add(new SearchRecord("中国古代玉器艺术展","南13展厅"));
        records.add(new SearchRecord("中国古代书画展","南12展厅"));
        records.add(new SearchRecord("高山景行——孔子文化展","北8-北10展厅"));
        records.add(new SearchRecord("大师：澳大利亚树皮画艺术","北11展厅"));
        return records;
    }

    @Override
    public NavigationSearchActivity bindView() {
        //状态栏字体设为黑色
        ScreenUtil.setAndroidNativeLightStatusBar(NavigationSearchActivity.this, true);
        //设置浏览记录为空情况下的 “去这里”和“清空浏览记录”的显示状态
        if (recordModel.getRecords().getValue().size() == 0){
            mBinding.navigationSearchGoTv.setVisibility(View.GONE);
            mBinding.navigationSearchClearTv.setVisibility(View.GONE);

        }else if (recordModel.getRecords().getValue().size() != 0){
            mBinding.navigationSearchGoTv.setVisibility(View.VISIBLE);
            mBinding.navigationSearchClearTv.setVisibility(View.VISIBLE);
        }


        return this;
    }

    @Override
    public NavigationSearchActivity bindData() {
        searchRecordAdapter = new NavigationSearchRecordAdapter(recordList);
        return this;
    }

    @Override
    public NavigationSearchActivity bindEvent() {

        //返回键
        mBinding.navigationSearchBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //“去这里” 点击事件
        mBinding.navigationSearchGoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NavigationSearchActivity.this,NavigationGoRoutesActivity.class);
                startActivity(intent);
            }
        });
        return this;
    }
}
