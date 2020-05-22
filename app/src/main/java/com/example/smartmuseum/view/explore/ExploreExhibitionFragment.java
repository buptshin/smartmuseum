package com.example.smartmuseum.view.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.MyExhibitionItemDecoration;
import com.example.smartmuseum.adapter.MyRecyclerViewAdapter;
import com.example.smartmuseum.databinding.ExploreExhibitionFragmentBinding;
import com.example.smartmuseum.databinding.FragmentExploreRecommendedrouteBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.Collection;
import com.example.smartmuseum.model.Exhibition;
import com.example.smartmuseum.view.GlobalVariables;
import com.example.smartmuseum.viewmodel.ExhibitionViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class ExploreExhibitionFragment extends Fragment implements ViewChainedBinding {
    private ExploreExhibitionFragmentBinding exploreExhibitionFragmentBinding;
    private ArrayList<Exhibition> latest_exhibition_list = new ArrayList<>(); // 最新展览对象列表
    private ArrayList<Exhibition> recommended_exhibition_list = new ArrayList<>();  // 推荐展览列表

    public static ExploreExhibitionFragment getInstance() {
        ExploreExhibitionFragment exploreExhibitionFragment = new ExploreExhibitionFragment();
        return exploreExhibitionFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        exploreExhibitionFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.explore_exhibition_fragment,
                container,
                false);
        View v = exploreExhibitionFragmentBinding.getRoot();
        initLatestRecyclerView();
        this.bindData().bindView().bindEvent();
        return v;
    }

    // 创建最新展览RecyclerView
    public void initLatestRecyclerView() {
        latest_exhibition_list.clear();
        //recyclerView创建
        initLatestItemList(latest_exhibition_list);
        //创建布局管理器，垂直设置LinearLayoutManager.VERTICAL，水平设置LinearLayoutManager.HORIZONTAL
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        //创建适配器，将数据传递给适配器
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(latest_exhibition_list);
        //设置布局管理器
        exploreExhibitionFragmentBinding.exhibitionRvList.setLayoutManager(mLinearLayoutManager);
//        //设置适配器adapter
//        mRecycleView.setAdapter(mAdapter);
        exploreExhibitionFragmentBinding.exhibitionRvList.setAdapter(adapter);

        // 添加item边距
        exploreExhibitionFragmentBinding.exhibitionRvList.addItemDecoration(new MyExhibitionItemDecoration());
    }

    // 创建推荐展览RecyclerView
    public void initRecommendedRecyclerView() {
        recommended_exhibition_list.clear();
        //recyclerView创建
        initRecommendedItemList(recommended_exhibition_list);
        //创建布局管理器，垂直设置LinearLayoutManager.VERTICAL，水平设置LinearLayoutManager.HORIZONTAL
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        //创建适配器，将数据传递给适配器
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(recommended_exhibition_list);
        //设置布局管理器
        exploreExhibitionFragmentBinding.exhibitionRvList.setLayoutManager(mLinearLayoutManager);
//        //设置适配器adapter
//        mRecycleView.setAdapter(mAdapter);
        exploreExhibitionFragmentBinding.exhibitionRvList.setAdapter(adapter);

        // 添加item边距
        exploreExhibitionFragmentBinding.exhibitionRvList.addItemDecoration(new MyExhibitionItemDecoration());
    }

    // 展览对象创建
    public void initLatestItemList(ArrayList<Exhibition> list) {
        Exhibition exhibition2 = new Exhibition("古代中国", R.drawable.mainpage_ancient_china, "基本陈列（常设）", 293993924,
                0, "地下一层展厅", 3.5, 240);

        Exhibition exhibition1 = new Exhibition("回归之路", R.drawable.mainpage_return_road, "2019.9.17-11.27", 223904,
                1, "北二，北三展厅", 1.5, 130);
        list.add(exhibition1);
        list.add(exhibition2);

    }

    public void initRecommendedItemList(ArrayList<Exhibition> list) {
        Exhibition exhibition1 = new Exhibition("古代中国", R.drawable.mainpage_ancient_china, "基本陈列（常设）", 293993924,
                0, "地下一层展厅", 3.5, 240);
        list.add(exhibition1);
        Exhibition exhibition2 = new Exhibition("回归之路", R.drawable.mainpage_return_road, "2019.9.17-11.27", 223904,
                1, "北二，北三展厅", 1.5, 130);
        list.add(exhibition2);
    }

    @Override
    public ExploreExhibitionFragment bindView() {
        return this;
    }

    @Override
    public ExploreExhibitionFragment bindData() {
        ExhibitionViewModel exhibtiionViewModel = new ViewModelProvider(this).get(ExhibitionViewModel.class);
        HashMap<String, String> map = new HashMap<>();
        exhibtiionViewModel.getExhibition_list(map).observe(getViewLifecycleOwner(), models -> {
            // do nothing

        });

        return this;
    }

    @Override
    public ExploreExhibitionFragment bindEvent() {

        exploreExhibitionFragmentBinding.recommendExhibitionOrNot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    exploreExhibitionFragmentBinding.recommendExhibitionOrNot.setText("最新展览");
                    initLatestRecyclerView();
                } else {
                    exploreExhibitionFragmentBinding.recommendExhibitionOrNot.setText("推荐展览");
                    initRecommendedRecyclerView();
                }
            }
        });
        return this;
    }
}
