package com.example.smartmuseum.view.explore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityExhibitionContentBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.Exhibition;
import com.example.smartmuseum.view.mainpage.MainActivity;
import com.example.smartmuseum.view.me.FieldGuideActivity;
import com.example.smartmuseum.viewmodel.ExhibitionViewModel;

import java.util.HashMap;
import java.util.List;

/*
 *lzg
 * 展厅详细页面
 */
public class ExhibitionContentActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityExhibitionContentBinding activityExhibitionContentBinding;
    private List<Exhibition> exhibitions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public ExhibitionContentActivity bindView() {
        activityExhibitionContentBinding = DataBindingUtil.setContentView(this, R.layout.activity_exhibition_content);
        return this;
    }

    @Override
    public ExhibitionContentActivity bindData() {
        // 绑定viewmodel
        ExhibitionViewModel exhibitionViewModel = new ViewModelProvider(this).get(ExhibitionViewModel.class);
        HashMap<String, String> map = new HashMap<>();

        // 调用数据（Activity里用this,fragment里面用getViewLifecycleOwner()
        exhibitionViewModel.getExhibition_list(map).observe(this, models -> {
            exhibitions = models;
            activityExhibitionContentBinding.exhibitionName.setText(exhibitions.get(0).getExhibition_name());
            activityExhibitionContentBinding.exhibitionHall.setText(exhibitions.get(0).getExhibition_hall());
        });

        return this;
    }

    @Override
    public ExhibitionContentActivity bindEvent() {
        activityExhibitionContentBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExhibitionContentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        activityExhibitionContentBinding.fgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExhibitionContentActivity.this, FieldGuideActivity.class);
                startActivity(intent);
            }
        });

        return this;
    }


}
