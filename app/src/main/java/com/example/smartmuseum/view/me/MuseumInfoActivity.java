package com.example.smartmuseum.view.me;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityMuseumInfoBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;
import com.example.smartmuseum.view.explore.ExhibitionContentActivity;

public class MuseumInfoActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityMuseumInfoBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public MuseumInfoActivity bindView() {
        //状态栏字体设为黑色
        ScreenUtil.setAndroidNativeLightStatusBar(MuseumInfoActivity.this, true);
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
