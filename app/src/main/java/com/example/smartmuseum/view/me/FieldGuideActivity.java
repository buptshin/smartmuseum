package com.example.smartmuseum.view.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityFieldGuideBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
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
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_field_guide);
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
