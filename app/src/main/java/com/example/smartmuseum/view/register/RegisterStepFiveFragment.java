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
import com.example.smartmuseum.databinding.FragmentRegisterStepFiveBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.view.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterStepFiveFragment extends Fragment implements ViewChainedBinding {

    private FragmentRegisterStepFiveBinding mBinding;

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
        return this;
    }

    @Override
    public RegisterStepFiveFragment bindEvent() {
        mBinding.registerBackImg.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(v);
            controller.navigate(R.id.action_registerStepFiveFragment_to_registerStepFourFragment);
        });

        mBinding.registerNextStepTv.setOnClickListener(v -> {
            // 再下一步就是回到登录界面了
            Intent intent = new Intent(mBinding.getRoot().getContext(), LoginActivity.class);
            getActivity().finish();
            startActivity(intent);
        });
        return this;
    }
}
