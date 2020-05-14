package com.example.smartmuseum.view.forgetpwd;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartmuseum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPwdResetFragment extends Fragment {


    public ForgetPwdResetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forget_pwd_reset, container, false);
    }

}
