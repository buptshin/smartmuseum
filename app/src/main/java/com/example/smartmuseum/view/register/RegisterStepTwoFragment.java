package com.example.smartmuseum.view.register;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentRegisterStepTwoBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterStepTwoFragment extends Fragment implements ViewChainedBinding {

    private FragmentRegisterStepTwoBinding mBinding;

    public RegisterStepTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_register_step_two, container, false);
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_register_step_two,
                container,
                false);
        this.bindData().bindView().bindEvent();
        return mBinding.getRoot();
    }

    @Override
    public RegisterStepTwoFragment bindView() {
        return this;
    }

    @Override
    public RegisterStepTwoFragment bindData() {
        return this;
    }

    @Override
    public RegisterStepTwoFragment bindEvent() {
        mBinding.registerBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_registerStepTwoFragment_to_registerStepOneFragment);
            }
        });

        mBinding.registerNextStepTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_registerStepTwoFragment_to_registerStepThreeFragment);
            }
        });
        return this;
    }
}
