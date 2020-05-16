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
import com.example.smartmuseum.databinding.FragmentRegisterStepFourBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterStepFourFragment extends Fragment implements ViewChainedBinding {

    private FragmentRegisterStepFourBinding mBinding;

    public RegisterStepFourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_register_step_four, container, false);
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_register_step_four,
                container,
                false);
        this.bindData().bindView().bindEvent();
        return mBinding.getRoot();
    }

    @Override
    public RegisterStepFourFragment bindView() {
        return this;
    }

    @Override
    public RegisterStepFourFragment bindData() {
        return this;
    }

    @Override
    public RegisterStepFourFragment bindEvent() {
        mBinding.registerBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_registerStepFourFragment_to_registerStepThreeFragment);
            }
        });

        mBinding.registerNextStepTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_registerStepFourFragment_to_registerStepFiveFragment);
            }
        });
        return this;
    }
}
