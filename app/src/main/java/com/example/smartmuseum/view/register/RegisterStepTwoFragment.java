package com.example.smartmuseum.view.register;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentRegisterStepTwoBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.viewmodel.OnRegisterUserViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterStepTwoFragment extends Fragment implements ViewChainedBinding {

    private FragmentRegisterStepTwoBinding mBinding;
    private OnRegisterUserViewModel onRegisterUserViewModel;

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

        // 选择头像
        mBinding.registerPersonalPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
                // 完善注册用户的信息
                if (updateRegisterUser()){
                    NavController controller = Navigation.findNavController(v);
                    controller.navigate(R.id.action_registerStepTwoFragment_to_registerStepThreeFragment);
                }
            }
        });

        mBinding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.reigster_male_rb)
                    mBinding.registerSelectedGenderHintTv.setText(R.string.register_gender_selected_hint_tv_male);
                else mBinding.registerSelectedGenderHintTv.setText(R.string.register_gender_selected_hint_tv_female);
            }
        });
        return this;
    }

    private boolean updateRegisterUser() {
        String userName = mBinding.registerNameEt.getText().toString();
        String userNickName = mBinding.registerNicknameEt.getText().toString();
        if (userName.isEmpty() || userNickName.isEmpty()){
            Toast toast = Toast.makeText(requireActivity(),"请填写完整信息",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }else {
            onRegisterUserViewModel = new ViewModelProvider(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),this)).get(OnRegisterUserViewModel.class);
            onRegisterUserViewModel.getOnRegisterUserInfo().getValue().setUserGender(mBinding.reigsterFemaleRb.isChecked()?0:1);
            onRegisterUserViewModel.getOnRegisterUserInfo().getValue().setNickName(userNickName);
            onRegisterUserViewModel.getOnRegisterUserInfo().getValue().setUserName(userName);
        }
        return true;
    }
}
