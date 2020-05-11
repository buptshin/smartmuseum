package com.example.smartmuseum.view.mainpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.MyRecyclerViewAdapter;
import com.example.smartmuseum.databinding.FragmentMainpageExploreBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.Exhibition;

import java.util.ArrayList;

public class MainPageExploreFragment extends Fragment implements ViewChainedBinding {

    private FragmentMainpageExploreBinding mBinding;

    private ArrayList<Exhibition> exhibition_list = new ArrayList<>();  // 展览对象类



    public static MainPageExploreFragment getInstance() {
        MainPageExploreFragment fragment = new MainPageExploreFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_mainpage_explore,
                container,
                false);
        View v = mBinding.getRoot();

        initItemList(exhibition_list);
        //创建布局管理器，垂直设置LinearLayoutManager.VERTICAL，水平设置LinearLayoutManager.HORIZONTAL
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        //创建适配器，将数据传递给适配器
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(exhibition_list);
        //设置布局管理器
        mBinding.exhibitionRvList.setLayoutManager(mLinearLayoutManager);
//        //设置适配器adapter
//        mRecycleView.setAdapter(mAdapter);
        mBinding.exhibitionRvList.setAdapter(adapter);

        this.bindData().bindView().bindEvent();
        return v;
    }

    public void initItemList(ArrayList<Exhibition> list) {
        Exhibition exhibition1 = new Exhibition("古代中国", R.drawable.mainpage_ancient_china, "基本陈列（常设）", 293993924,
                R.mipmap.mainpage_exhibition_like_not_selected, "地下一层展厅", 3.5, 240);
        list.add(exhibition1);
        Exhibition exhibition2 = new Exhibition("回归之路", R.drawable.mainpage_return_road, "2019.9.17-11.27", 223904,
                R.mipmap.mainpage_exhibition_like_selected, "北二，北三展厅", 1.5, 130);
        list.add(exhibition2);
    }

    @Override
    public MainPageExploreFragment bindData() {
        return this;
    }

    @Override
    public MainPageExploreFragment bindView() {
        return this;
    }

    @Override
    public MainPageExploreFragment bindEvent() {
        return null;
    }
}
