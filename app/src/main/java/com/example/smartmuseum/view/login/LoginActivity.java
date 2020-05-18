package com.example.smartmuseum.view.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityLoginBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;
import com.example.smartmuseum.view.forgetpwd.ForgetPwdActivity;
import com.example.smartmuseum.view.mainpage.MainActivity;
import com.example.smartmuseum.view.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public LoginActivity bindView() {
        ScreenUtil.fullScreen(LoginActivity.this);
        return this;
    }

    @Override
    public LoginActivity bindData() {
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        return this;
    }

    @Override
    public LoginActivity bindEvent() {

        //“注册”
        mBinding.loginToRegisterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // “登录”
        mBinding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // “忘记密码”
        mBinding.loginToForgetpwdTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgetPwdActivity.class);
                startActivity(intent);
            }
        });
        return this;
    }
}