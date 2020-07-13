package com.example.smartmuseum.view.forgetpwd;


import android.content.Intent;
import android.media.audiofx.DynamicsProcessing;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentForgetPwdResetBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.view.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPwdResetFragment extends Fragment implements ViewChainedBinding {

    private FragmentForgetPwdResetBinding mBinding;
    private boolean hidePwd = true;
    private boolean hideAssure = true;

    public ForgetPwdResetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_forget_pwd_reset, container, false);
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_forget_pwd_reset,
                container,false
        );
        this.bindData().bindView().bindEvent();
        return mBinding.getRoot();
    }

    @Override
    public ForgetPwdResetFragment bindView() {
        return this;
    }

    @Override
    public ForgetPwdResetFragment bindData() {
        return this;
    }

    @Override
    public ForgetPwdResetFragment bindEvent() {
        mBinding.forgetpwdBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        mBinding.forgetpwdChangePwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mBinding.getRoot().getContext(), LoginActivity.class);
                getActivity().finish();
                startActivity(intent);
            }
        });

        // 输入新密码的显示与隐藏
        mBinding.forgetpwdNewPasswordPreviewImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidePwd = !hidePwd;
                if(!hidePwd){
                    mBinding.forgetpwdNewPasswordPreviewImg.setImageResource(R.mipmap.login_password_hide);
                    mBinding.forgetpwdNewPasswordEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    mBinding.forgetpwdNewPasswordPreviewImg.setImageResource(R.mipmap.login_password_preview);
                    mBinding.forgetpwdNewPasswordEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                String registerPwd = mBinding.forgetpwdNewPasswordEt.getText().toString();
                mBinding.forgetpwdNewPasswordEt.setSelection(registerPwd.length());
            }
        });

        // 确认新密码的显示与隐藏
        mBinding.forgetpwdAssurePwdPreviewImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAssure = !hideAssure;
                if(!hideAssure){
                    mBinding.forgetpwdAssurePwdPreviewImg.setImageResource(R.mipmap.login_password_hide);
                    mBinding.forgetpwdAssurePwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    mBinding.forgetpwdAssurePwdPreviewImg.setImageResource(R.mipmap.login_password_preview);
                    mBinding.forgetpwdAssurePwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                String registerAssurePwd = mBinding.forgetpwdAssurePwdEt.getText().toString();
                mBinding.forgetpwdAssurePwdEt.setSelection(registerAssurePwd.length());
            }
        });
        return this;
    }
}
