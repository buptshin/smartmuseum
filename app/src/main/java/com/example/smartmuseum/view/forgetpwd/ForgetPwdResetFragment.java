package com.example.smartmuseum.view.forgetpwd;


import android.content.Intent;
import android.media.audiofx.DynamicsProcessing;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

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
        return this;
    }
}
