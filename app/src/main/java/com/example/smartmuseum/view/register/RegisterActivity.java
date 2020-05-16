package com.example.smartmuseum.view.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.speech.RecognizerIntent;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityRegisterBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;

public class RegisterActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityRegisterBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
        // setContentView(R.layout.activity_register);
    }

    @Override
    public RegisterActivity bindView() {
        ScreenUtil.fullScreen(RegisterActivity.this);
        return this;
    }

    @Override
    public RegisterActivity bindData() {
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_register);
        return this;
    }

    @Override
    public RegisterActivity bindEvent() {
        return this;
    }
}
