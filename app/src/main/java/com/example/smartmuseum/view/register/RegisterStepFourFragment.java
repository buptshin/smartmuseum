package com.example.smartmuseum.view.register;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentRegisterStepFourBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.OnRegisterUser;
import com.example.smartmuseum.util.RegexUtils;
import com.example.smartmuseum.viewmodel.OnRegisterUserViewModel;

import java.util.PrimitiveIterator;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterStepFourFragment extends Fragment implements ViewChainedBinding {

    private FragmentRegisterStepFourBinding mBinding;
    private OnRegisterUserViewModel onRegisterUserViewModel;

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
        onRegisterUserViewModel = new ViewModelProvider(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),this)).get(OnRegisterUserViewModel.class);
        return this;
    }

    @Override
    public RegisterStepFourFragment bindEvent() {

        onRegisterUserViewModel.getOnRegisterUserInfo().observe(requireActivity(), new Observer<OnRegisterUser>() {
            @Override
            public void onChanged(OnRegisterUser onRegisterUser) {
                mBinding.registerTelephoneEt.setText(onRegisterUser.getUserTel());
            }
        });
        
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
                if (updateRegisterUser()){
                    NavController controller = Navigation.findNavController(v);
                    controller.navigate(R.id.action_registerStepFourFragment_to_registerStepFiveFragment);
                }
            }
        });
        return this;
    }

    private boolean updateRegisterUser() {
        String registerTel = mBinding.registerTelephoneEt.getText().toString();
        if (registerTel.isEmpty()){
            Toast toast = Toast.makeText(requireActivity(),"手机号为空",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }else if (!RegexUtils.validateMobilePhone(registerTel)){
            Toast toast = Toast.makeText(requireActivity(),"请输入正确的手机号",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        onRegisterUserViewModel.getOnRegisterUserInfo().getValue().setUserTel(registerTel);
        return true;
    }
}
