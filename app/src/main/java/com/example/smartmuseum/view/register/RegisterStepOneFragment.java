package com.example.smartmuseum.view.register;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.FragmentRegisterStepOneBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.model.OnRegisterUser;
import com.example.smartmuseum.util.OnSoftGlobalLayoutListener;
import com.example.smartmuseum.util.OnSoftKeyboardStateChangedListener;
import com.example.smartmuseum.util.RegexUtils;
import com.example.smartmuseum.view.login.LoginActivity;
import com.example.smartmuseum.viewmodel.OnRegisterUserViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterStepOneFragment extends Fragment implements ViewChainedBinding {

    private FragmentRegisterStepOneBinding mBinding;
    private boolean hidePwd = true;
    private boolean hideAssure = true;
    private OnSoftGlobalLayoutListener onSoftGlobalLayoutListener;
    private float registerBoxY;
    private OnRegisterUserViewModel onRegisterUserViewModel;

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
    public void onDestroyView() {
        getActivity().getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(onSoftGlobalLayoutListener);
        super.onDestroyView();
    }

    @Override
    public RegisterStepOneFragment bindView() {
        getActivity().getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(onSoftGlobalLayoutListener);
        mBinding.registerMuseumBgIv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mBinding.registerMuseumBgIv,"translationX",0,-80,0,80,0);
                objectAnimator.setDuration(10000);
                objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
                objectAnimator.start();
                mBinding.registerMuseumBgIv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        return this;
    }

    @Override
    public RegisterStepOneFragment bindData() {

        // 获取注册输入框的Y
        ViewTreeObserver registerVt = mBinding.registerMainBox.getViewTreeObserver();
        registerVt.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                registerBoxY = mBinding.registerMainBox.getY();
                mBinding.registerMainBox.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        onSoftGlobalLayoutListener = new OnSoftGlobalLayoutListener(new OnSoftKeyboardStateChangedListener() {
            @Override
            public void OnSoftKeyboardStateChanged(boolean isKeyboardShow, int keyboardHeight, int screenHeight) {
                if (isKeyboardShow){
                    mBinding.registerMainBox.setY(mBinding.registerWelcomeTv.getY());
                }else {
                    mBinding.registerMainBox.setY(registerBoxY);
                }
            }
        }, requireActivity());
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
                // “注册”当前用户的信息
                if (getRegisterUserInfo()){
                    // 跳转
                    NavController controller = Navigation.findNavController(v);
                    controller.navigate(R.id.action_registerStepOneFragment_to_registerStepTwoFragment);
                }
            }
        });

        // 输入密码的显示与隐藏
        mBinding.registerPasswordPreviewImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidePwd = !hidePwd;
                if(!hidePwd){
                    mBinding.registerPasswordPreviewImg.setImageResource(R.mipmap.login_password_hide);
                    mBinding.registerPasswordEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    mBinding.registerPasswordPreviewImg.setImageResource(R.mipmap.login_password_preview);
                    mBinding.registerPasswordEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                String registerPwd = mBinding.registerPasswordEt.getText().toString();
                mBinding.registerPasswordEt.setSelection(registerPwd.length());
            }
        });

        // 确认密码的显示与隐藏
        mBinding.registerAssurePwdPreviewImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAssure = !hideAssure;
                if(!hideAssure){
                    mBinding.registerAssurePwdPreviewImg.setImageResource(R.mipmap.login_password_hide);
                    mBinding.registerAssurePwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    mBinding.registerAssurePwdPreviewImg.setImageResource(R.mipmap.login_password_preview);
                    mBinding.registerAssurePwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                String registerAssurePwd = mBinding.registerAssurePwdEt.getText().toString();
                mBinding.registerAssurePwdEt.setSelection(registerAssurePwd.length());
            }
        });
        return this;
    }

    private boolean getRegisterUserInfo() {
        String registerTel = mBinding.registerAccountTelephoneEt.getText().toString();
        String registerPwd = mBinding.registerPasswordEt.getText().toString();
        String registerAssurePwd = mBinding.registerAssurePwdEt.getText().toString();
        if (registerAssurePwd.isEmpty() || registerTel.isEmpty() || registerPwd.isEmpty()){
            Toast toast = Toast.makeText(requireActivity(),"请填写完整信息",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }else if (!registerAssurePwd.equals(registerPwd)){
            Toast toast = Toast.makeText(requireActivity(),"密码不一致",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }else if (!RegexUtils.validateMobilePhone(registerTel)){
            Toast toast = Toast.makeText(requireActivity(),"请输入正确的手机号",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }else {
            onRegisterUserViewModel = new ViewModelProvider(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),this)).get(OnRegisterUserViewModel.class);
            OnRegisterUser curUser = new OnRegisterUser();
            curUser.setUserTel(registerTel);
            curUser.setUserPwd(registerPwd);
            onRegisterUserViewModel.getOnRegisterUserInfo().setValue(curUser);
        }
        return true;
    }
}
