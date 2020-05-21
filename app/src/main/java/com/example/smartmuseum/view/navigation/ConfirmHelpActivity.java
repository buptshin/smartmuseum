package com.example.smartmuseum.view.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

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
        this.bindData().bindView().bindEvent();
    }

    @Override
    public ConfirmHelpActivity bindView() {
        mBinding = DataBindingUtil.setContentView(ConfirmHelpActivity.this, R.layout.activity_confirm_help);
        //设为全屏
        ScreenUtil.fullScreen(ConfirmHelpActivity.this);

        return this;
    }

    @Override
    public ConfirmHelpActivity bindData() {
        return this;
    }

    @Override
    public ConfirmHelpActivity bindEvent() {

        mBinding.confirmHelpLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mBinding.confirmHelpClickRl.setOnClickListener(new View.OnClickListener() {
            //需要监听几次点击事件数组的长度就为几
            //如果要监听双击事件则数组长度为2，如果要监听3次连续点击事件则数组长度为3...
            long[] mHints = new long[3];//初始全部为0
            @Override
            public void onClick(View view) {
                //将mHints数组内的所有元素左移一个位置
                System.arraycopy(mHints, 1, mHints, 0, mHints.length - 1);
                //获得当前系统已经启动的时间
                mHints[mHints.length - 1] = SystemClock.uptimeMillis();
                //三次点击总时间不得超过1500ms
                if(SystemClock.uptimeMillis()-mHints[0]<=1500) {
                    Intent intent = new Intent(ConfirmHelpActivity.this, InHelpingActivity.class);
                    startActivity(intent);
                    finish();
                }
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
        windowParams.alpha = 0.9f;//０全透明．1不透明．
        window.setAttributes(windowParams);
    }
}
