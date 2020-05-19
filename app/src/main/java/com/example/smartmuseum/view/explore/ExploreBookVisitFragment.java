package com.example.smartmuseum.view.explore;

import android.os.Bundle;
import android.text.Layout;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartmuseum.R;
import com.example.smartmuseum.adapter.MainPageBookServiceListAdapter;
import com.example.smartmuseum.databinding.FragmentExploreBookvisitBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.viewmodel.BookVisitViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExploreBookVisitFragment extends Fragment implements ViewChainedBinding, View.OnClickListener {

    private FragmentExploreBookvisitBinding fragmentExploreBookvisitBinding;
    // 预定页面ViewModel：暂时只有预定人数
    private BookVisitViewModel viewModel;

    private List<String> mData = new ArrayList<>();
    private MainPageBookServiceListAdapter adapter;
    private SparseBooleanArray stateCheckMap = new SparseBooleanArray();  // 存放checkbox选中状态
    private List<String> mCheckedData = new ArrayList<>();  // 使用list存放选中的内容


    public static ExploreBookVisitFragment getInstance() {
        ExploreBookVisitFragment exploreBookVisitFragment = new ExploreBookVisitFragment();
        return exploreBookVisitFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentExploreBookvisitBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_explore_bookvisit,
                container,
                false);
        View v = fragmentExploreBookvisitBinding.getRoot();
        this.bindData().bindView().bindEvent();
        return v;
    }

    @Override
    public ExploreBookVisitFragment bindView() {
        adapter = new MainPageBookServiceListAdapter(this.getContext(), mData, stateCheckMap);
        fragmentExploreBookvisitBinding.serviceList.setAdapter(adapter);
        fragmentExploreBookvisitBinding.serviceList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        return this;
    }

    @Override
    public ExploreBookVisitFragment bindData() {
        mData.add("无障碍设施");
        mData.add("导览讲解");
        mData.add("物品寄存");

        return this;
    }

    @Override
    public ExploreBookVisitFragment bindEvent() {
        // 增加游客
        fragmentExploreBookvisitBinding.plusPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(fragmentExploreBookvisitBinding.peopleNumber.getText().toString());
                if (num >= 0) {
                    num++;
                    fragmentExploreBookvisitBinding.peopleNumber.setText(num + "");
                }
            }
        });
        // 减少游客
        fragmentExploreBookvisitBinding.minusPassengers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(fragmentExploreBookvisitBinding.peopleNumber.getText().toString());
                if (num > 0) {
                    num--;
                    fragmentExploreBookvisitBinding.peopleNumber.setText(num + "");
                }
            }
        });
        return this;
    }

    @Override
    public void onClick(View view) {

    }

    private void setOnListViewClickListener() {
        fragmentExploreBookvisitBinding.serviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                updateCheckBoxStatus(view, position);
            }
        });
    }

    private void updateCheckBoxStatus(View view, int position) {
        MainPageBookServiceListAdapter.ViewHolder holder = (MainPageBookServiceListAdapter.ViewHolder) view.getTag();
        holder.checkBox.toggle();  // 反转checkbox的选中状态
        fragmentExploreBookvisitBinding.serviceList.setItemChecked(position, holder.checkBox.isChecked());
        stateCheckMap.put(position, holder.checkBox.isChecked());  // SparseArray这样用
        if (holder.checkBox.isChecked()) {
            mCheckedData.add(mData.get(position));  // 将选中的list项放入mCheckedData列表中
        } else {
            mCheckedData.remove(mData.get(position));  // 未选中的list移除列表
        }
        adapter.notifyDataSetChanged();
    }
}
