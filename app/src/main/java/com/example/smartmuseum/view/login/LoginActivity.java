package com.example.smartmuseum.view.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityLoginBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.OnSoftGlobalLayoutListener;
import com.example.smartmuseum.util.OnSoftKeyboardStateChangedListener;
import com.example.smartmuseum.util.ScreenUtil;
import com.example.smartmuseum.view.forgetpwd.ForgetPwdActivity;
import com.example.smartmuseum.view.mainpage.MainActivity;
import com.example.smartmuseum.view.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements ViewChainedBinding {

    private ActivityLoginBinding mBinding;
    private boolean hidePwd = true;   // 密码的隐藏
    private OnSoftGlobalLayoutListener onSoftGlobalLayoutListener;
    private float loginBoxY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(onSoftGlobalLayoutListener);
    }

    @Override
    public LoginActivity bindView() {
        ScreenUtil.fullScreen(LoginActivity.this);
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(onSoftGlobalLayoutListener);
        mBinding.loginMuseumBgIv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mBinding.loginMuseumBgIv,"translationX",0,-80,0,80,0);
                objectAnimator.setDuration(10000);
                objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
                objectAnimator.start();
                mBinding.loginMuseumBgIv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        return this;
    }

    @Override
    public LoginActivity bindData() {
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        // 获取输入框的正常Y值和高度
        ViewTreeObserver loginVt  = mBinding.loginMainBox.getViewTreeObserver();
        loginVt.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                loginBoxY = mBinding.loginMainBox.getY();
                mBinding.loginMainBox.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        onSoftGlobalLayoutListener = new OnSoftGlobalLayoutListener(new OnSoftKeyboardStateChangedListener() {
            @Override
            public void OnSoftKeyboardStateChanged(boolean isKeyboardShow, int keyboardHeight, int screenHeight) {
                if (isKeyboardShow){
                    // 如果软键盘是打开的，则上移账号信息的输入块
                    mBinding.loginMainBox.setY(mBinding.loginWelcomeTv.getY());
                }else {
                    // 如果软键盘变为隐藏，则还原
                    mBinding.loginMainBox.setY(loginBoxY);
                }
            }
        }, this);
        return this;
    }

    @Override
    public LoginActivity bindEvent() {

        // 从“注册”界面跳转
        String registerUserTel = getIntent().getStringExtra("registerUserTel");
        if (registerUserTel!=null && !registerUserTel.isEmpty())
            mBinding.loginAccountTelephoneEt.setText(registerUserTel);

        //“注册”
        mBinding.loginToRegisterTv.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // “登录”
        mBinding.loginBtn.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // “忘记密码”
        mBinding.loginToForgetpwdTv.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgetPwdActivity.class);
            startActivity(intent);
        });

        // 密码的显示与隐藏
        mBinding.loginPasswordPreviewImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidePwd = !hidePwd;
                if(!hidePwd){
                    // 隐藏
                    mBinding.loginPasswordPreviewImg.setImageResource(R.mipmap.login_password_hide);
                    mBinding.loginPasswordEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    // 显示
                    mBinding.loginPasswordPreviewImg.setImageResource(R.mipmap.login_password_preview);
                    mBinding.loginPasswordEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                // 改变光标的位置
                String loginPwd = mBinding.loginPasswordEt.getText().toString();
                mBinding.loginPasswordEt.setSelection(loginPwd.length());
            }
        });
        return this;
    }
}
