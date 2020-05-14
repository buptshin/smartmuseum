package com.example.smartmuseum.view.explore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityExhibitionContentBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.Exhibition;
import com.example.smartmuseum.viewmodel.ExhibitionViewModel;

import java.util.HashMap;

/*
 *lzg
 * 展厅详细页面
 */
public class ExhibitionContentActivity extends AppCompatActivity implements ViewChainedBinding<ExhibitionContentActivity> {

    private ActivityExhibitionContentBinding activityExhibitionContentBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public ExhibitionContentActivity bindView() {
        return null;
    }

    @Override
    public ExhibitionContentActivity bindData() {
        activityExhibitionContentBinding = DataBindingUtil.setContentView(this, R.layout.activity_exhibition_content);
        // 绑定viewmodel
        ExhibitionViewModel viewModel = new ViewModelProvider(this).get(ExhibitionViewModel.class);
        //设置数据反向绑定
//        activityExhibitionContentBinding.setExhibition(viewModel);
        HashMap<String, String> map = new HashMap<>();

        return null;
    }

    @Override
    public ExhibitionContentActivity bindEvent() {
        return null;
    }


}
