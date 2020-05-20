package com.example.smartmuseum.view.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityFieldGuideBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;
import com.example.smartmuseum.view.mainpage.MainActivity;

public class FieldGuideActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityFieldGuideBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }


    @Override
    public FieldGuideActivity bindView() {
        // 设置activity theme去除状态栏
        this.setTheme(R.style.AppTheme);
        // 状态栏字体设为黑色
        ScreenUtil.setAndroidNativeLightStatusBar(FieldGuideActivity.this, true);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_field_guide);
        mBinding.scrollView3.setFadingEdgeLength(0);
        return this;
    }

    @Override
    public FieldGuideActivity bindData() {
        return this;
    }

    @Override
    public FieldGuideActivity bindEvent() {
        mBinding.imageView45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FieldGuideActivity.this.finish();
            }
        });
        return this;
    }
}
