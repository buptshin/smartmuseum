package com.example.smartmuseum.view.register;


import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentRegisterStepOneBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.view.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterStepOneFragment extends Fragment implements ViewChainedBinding {

    private FragmentRegisterStepOneBinding mBinding;

    public RegisterStepOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_register_step_one, container, false);
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_register_step_one,
                container,
                false);
        this.bindData().bindView().bindEvent();
        return mBinding.getRoot();
    }

    @Override
    public RegisterStepOneFragment bindView() {
        return this;
    }

    @Override
    public RegisterStepOneFragment bindData() {
        return this;
    }

    @Override
    public RegisterStepOneFragment bindEvent() {

        // 返回登录
        mBinding.registerToLoginTv.setOnClickListener(v -> {
            Intent intent = new Intent(mBinding.getRoot().getContext(),LoginActivity.class);
            getActivity().finish();
            // 在回到登录界面的时候，应该清空所有的Activity,这里先不写
            startActivity(intent);
        });

        // 进行注册的下一步操作
        mBinding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_registerStepOneFragment_to_registerStepTwoFragment);
            }
        });
        return this;
    }
}
