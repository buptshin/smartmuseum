package com.example.smartmuseum.view.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityConfirmHelpBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;
import com.example.smartmuseum.view.mainpage.MainActivity;

/*求救确认activity*/
public class ConfirmHelpActivity extends Activity implements ViewChainedBinding {
    private ActivityConfirmHelpBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public ConfirmHelpActivity bindView() {
        mBinding = DataBindingUtil.setContentView(ConfirmHelpActivity.this, R.layout.activity_confirm_help);

        //状态栏字体设为黑色
        ScreenUtil.fullScreen(ConfirmHelpActivity.this);
        return this;
    }

    @Override
    public ConfirmHelpActivity bindData() {
        return this;
    }

    @Override
    public ConfirmHelpActivity bindEvent() {
        return this;
    }
}
