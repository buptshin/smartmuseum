package com.example.smartmuseum.view.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivitySettingsBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;

public class SettingsActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivitySettingsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
        //setContentView(R.layout.activity_settings);
    }

    @Override
    public SettingsActivity bindView() {
        ScreenUtil.setAndroidNativeLightStatusBar(SettingsActivity.this,true);
        return this;
    }

    @Override
    public SettingsActivity bindData() {
        mBinding = DataBindingUtil.setContentView(SettingsActivity.this,R.layout.activity_settings);
        return this;
    }

    @Override
    public SettingsActivity bindEvent() {
        mBinding.settingsCloseImg.setOnClickListener(v -> finish());
        return this;
    }
}
