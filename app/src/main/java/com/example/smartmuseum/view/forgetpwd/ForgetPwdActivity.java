package com.example.smartmuseum.view.forgetpwd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityForgetPwdBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;

public class ForgetPwdActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityForgetPwdBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
        // setContentView(R.layout.activity_forget_pwd);
    }

    @Override
    public ForgetPwdActivity bindView() {
        ScreenUtil.fullScreen(this);
        return this;
    }

    @Override
    public ForgetPwdActivity bindData() {
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_forget_pwd);
        return this;
    }

    @Override
    public ForgetPwdActivity bindEvent() {
        return this;
    }
}
