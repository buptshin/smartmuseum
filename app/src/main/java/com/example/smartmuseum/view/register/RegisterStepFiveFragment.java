package com.example.smartmuseum.view.register;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentRegisterStepFiveBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.OnRegisterUser;
import com.example.smartmuseum.view.login.LoginActivity;
import com.example.smartmuseum.viewmodel.OnRegisterUserViewModel;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterStepFiveFragment extends Fragment implements ViewChainedBinding {

    private FragmentRegisterStepFiveBinding mBinding;
    private OnRegisterUserViewModel onRegisterUserViewModel;
    private String sendHintTv,toSendHintTv;

    public RegisterStepFiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_register_step_five, container, false);
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_register_step_five,
                container,
                false);
        this.bindData().bindView().bindEvent();
        return mBinding.getRoot();
    }

    @Override
    public RegisterStepFiveFragment bindView() {
        return this;
    }

    @Override
    public RegisterStepFiveFragment bindData() {
        onRegisterUserViewModel = new ViewModelProvider(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),this)).get(OnRegisterUserViewModel.class);
        return this;
    }

    @Override
    public RegisterStepFiveFragment bindEvent() {

        onRegisterUserViewModel.getOnRegisterUserInfo().observe(requireActivity(), new Observer<OnRegisterUser>() {
            @Override
            public void onChanged(OnRegisterUser onRegisterUser) {
                toSendHintTv = getResources().getString(R.string.register_assure_telephone_send_hint_tv);
                sendHintTv = getResources().getString(R.string.register_assure_telephone_hint_tv);
                sendHintTv = String.format(sendHintTv,onRegisterUser.getUserTel());
                toSendHintTv = String.format(toSendHintTv,onRegisterUser.getUserTel());
                mBinding.registerAssureTelephoneHintTv.setText(toSendHintTv);
            }
        });
        // 发送验证码
        mBinding.registerAssureTelephoneSendTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.registerAssureTelephoneSendTv.setVisibility(View.GONE);
                mBinding.registerAssureTelephoneHintTv.setText(sendHintTv);
                // 启动倒计时
                countDown();
            }
        });
        mBinding.registerBackImg.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(v);
            controller.navigate(R.id.action_registerStepFiveFragment_to_registerStepFourFragment);
        });

        mBinding.registerNextStepTv.setOnClickListener(v -> {
            // 再下一步就是回到登录界面了
            if (doRegister()){
                Intent intent = new Intent(mBinding.getRoot().getContext(), LoginActivity.class);
                intent.putExtra("registerUserTel",onRegisterUserViewModel.getOnRegisterUserInfo().getValue().getUserTel());
                getActivity().finish();
                startActivity(intent);
            }
        });

        // 设置验证码输入框的自动跳转
        // 1
        mBinding.registerVerifyCodeOneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!mBinding.registerVerifyCodeOneEt.getText().toString().isEmpty() && mBinding.registerVerifyCodeTwoEt.getText().toString().isEmpty())
                    mBinding.registerVerifyCodeTwoEt.requestFocus();
            }
        });
        // 2
        mBinding.registerVerifyCodeTwoEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!mBinding.registerVerifyCodeTwoEt.getText().toString().isEmpty() && mBinding.registerVerifyCodeThreeEt.getText().toString().isEmpty())
                    mBinding.registerVerifyCodeThreeEt.requestFocus();
            }
        });
        // 3
        mBinding.registerVerifyCodeThreeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!mBinding.registerVerifyCodeThreeEt.getText().toString().isEmpty() && mBinding.registerVerifyCodeFourEt.getText().toString().isEmpty())
                    mBinding.registerVerifyCodeFourEt.requestFocus();
            }
        });
        // 4
        mBinding.registerVerifyCodeFourEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!mBinding.registerVerifyCodeFourEt.getText().toString().isEmpty() && mBinding.registerVerifyCodeFiveEt.getText().toString().isEmpty())
                    mBinding.registerVerifyCodeFiveEt.requestFocus();
            }
        });
        // 5
        mBinding.registerVerifyCodeFiveEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return this;
    }

    private boolean doRegister() {
        // 注册用户
        Log.d("register",onRegisterUserViewModel.getOnRegisterUserInfo().getValue().toString());
        String verifyCode = mBinding.registerVerifyCodeOneEt.getText().toString()
                + mBinding.registerVerifyCodeTwoEt.getText().toString()
                + mBinding.registerVerifyCodeThreeEt.getText().toString()
                + mBinding.registerVerifyCodeFourEt.getText().toString()
                + mBinding.registerVerifyCodeFiveEt.getText().toString();
        HashMap map = new HashMap();
        map.put("registerVerifyCode", verifyCode);
        onRegisterUserViewModel.registerUser(map);
        return true;
    }

    private void countDown() {
        mBinding.reigsterAssureTelephoneChronometerTv.setVisibility(View.VISIBLE);
        mBinding.registerAssureTelephoneTimeHintTv.setVisibility(View.VISIBLE);
        CountDownTimer timer = new CountDownTimer(60*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mBinding.reigsterAssureTelephoneChronometerTv.setText(millisUntilFinished/1000+"秒");
            }

            @Override
            public void onFinish() {
                mBinding.registerAssureTelephoneSendTv.setText(R.string.register_assure_telephone_resend_tv);
                mBinding.registerAssureTelephoneSendTv.setVisibility(View.VISIBLE);
            }
        }.start();
    }
}
