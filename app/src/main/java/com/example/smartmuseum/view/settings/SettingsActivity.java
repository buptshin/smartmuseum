package com.example.smartmuseum.view.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.adapters.ViewBindingAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivitySettingsBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;
import com.example.smartmuseum.view.login.LoginActivity;

public class SettingsActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivitySettingsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_settings);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public SettingsActivity bindView() {
        ScreenUtil.fullScreen(SettingsActivity.this);
        return this;
    }

    @Override
    public SettingsActivity bindData() {
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_settings);
        return this;
    }

    @Override
    public SettingsActivity bindEvent() {
        mBinding.settingsLogoutBtn.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
            // 记得清空所有的activity
            startActivity(intent);
        });
        return this;
    }
}
