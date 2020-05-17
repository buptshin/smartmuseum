package com.example.smartmuseum.view.navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.smartmuseum.R;
import com.example.smartmuseum.databinding.ActivityNavigationSearchBinding;
import com.example.smartmuseum.handler.ViewChainedBinding;

public class NavigationSearchActivity extends AppCompatActivity implements ViewChainedBinding {
    ActivityNavigationSearchBinding searchBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_search);
    }

    @Override
    public Object bindView() {
        return null;
    }

    @Override
    public Object bindData() {
        return null;
    }

    @Override
    public Object bindEvent() {
        return null;
    }
}
