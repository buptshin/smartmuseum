package com.example.smartmuseum.view.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityInHelpingBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;


/*紧急求助中activity*/
public class InHelpingActivity extends AppCompatActivity  implements ViewChainedBinding {
    private ActivityInHelpingBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public InHelpingActivity bindView() {
        mBinding = DataBindingUtil.setContentView(InHelpingActivity.this, R.layout.activity_in_helping);
        //设为全屏
        ScreenUtil.fullScreen(InHelpingActivity.this);
        return this;
    }

    @Override
    public InHelpingActivity bindData() {
        return this;
    }

    @Override
    public InHelpingActivity bindEvent() {

        mBinding.inHelpingSafeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        return this;
    }

    /*设置透明度*/
    @Override
    public void onStart() {
        super.onStart();
        Window window = getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.alpha = 0.95f;//０全透明．1不透明．
        window.setAttributes(windowParams);
    }
}
