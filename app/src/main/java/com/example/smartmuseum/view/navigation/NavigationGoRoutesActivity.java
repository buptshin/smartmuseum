package com.example.smartmuseum.view.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityNavigationGoRoutesBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;
import com.example.smartmuseum.util.ScreenUtil;

/*路线导航activity*/
public class NavigationGoRoutesActivity extends AppCompatActivity implements ViewChainedBinding {
    private ActivityNavigationGoRoutesBinding mBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_navigation_go_routes);
        this.bindData().bindView().bindEvent();
    }

    @Override
    public NavigationGoRoutesActivity bindView() {
        //状态栏字体设为黑色
        ScreenUtil.setAndroidNativeLightStatusBar(NavigationGoRoutesActivity.this, true);
        return this;
    }

    @Override
    public NavigationGoRoutesActivity bindData() {
        return this;
    }

    @Override
    public NavigationGoRoutesActivity bindEvent() {
        mBinding.navigationRoutesBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        return this;
    }
}
