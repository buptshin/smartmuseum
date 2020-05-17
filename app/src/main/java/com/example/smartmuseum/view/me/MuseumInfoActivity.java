package com.example.smartmuseum.view.me;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityMuseumInfoBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;

public class MuseumInfoActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityMuseumInfoBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public MuseumInfoActivity bindView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_museum_info);
        return this;
    }

    @Override
    public MuseumInfoActivity bindData() {
        return this;
    }

    @Override
    public MuseumInfoActivity bindEvent() {
        mBinding.imageView25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MuseumInfoActivity.this.finish();
            }
        });
        return this;
    }
}
