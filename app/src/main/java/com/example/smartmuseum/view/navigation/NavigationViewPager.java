package com.example.smartmuseum.view.navigation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/*导览界面，禁止滑动切换和去除切换时动画效果viewpager*/
public class NavigationViewPager extends ViewPager {
    public NavigationViewPager(Context context) {
        super(context);
    }

    public NavigationViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item,false);
    }
}
