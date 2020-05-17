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
import com.example.smartmuseum.databinding.FragmentRegisterStepThreeBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterStepThreeFragment extends Fragment implements ViewChainedBinding {

    private FragmentRegisterStepThreeBinding mBinding;

    public RegisterStepThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_register_step_three, container, false);
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_register_step_three,
                container,
                false);
        this.bindData().bindView().bindEvent();
        return mBinding.getRoot();
    }

    @Override
    public RegisterStepThreeFragment bindView() {
        return this;
    }

    @Override
    public RegisterStepThreeFragment bindData() {
        return this;
    }

    @Override
    public RegisterStepThreeFragment bindEvent() {
        mBinding.registerBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_registerStepThreeFragment_to_registerStepTwoFragment);
            }
        });
        mBinding.registerNextStepTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_registerStepThreeFragment_to_registerStepFourFragment);
            }
        });
        return this;
    }
}
