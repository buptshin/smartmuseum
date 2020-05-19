package com.example.smartmuseum.view.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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
        mBinding.settingsLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                finish();  // 应该清空所有的activity
                startActivity(intent);
            }
        });
        return this;
    }
}
