package com.example.smartmuseum.util;

import android.app.Activity;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewTreeObserver;

public class OnSoftGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
    private OnSoftKeyboardStateChangedListener onSoftKeyboardStateChangedListener;
    private Activity activity;
    private int screenHeight;
    private boolean mIsSoftKeyboardShowing = false;

    // 构造方法
    public OnSoftGlobalLayoutListener(OnSoftKeyboardStateChangedListener onSoftKeyboardStateChangedListener, Activity activity){
        super();
        this.onSoftKeyboardStateChangedListener = onSoftKeyboardStateChangedListener;
        this.activity = activity;
        getScreenHeight();
    }

    // 获取屏幕的高度
    private void getScreenHeight(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
    }
    @Override
    public void onGlobalLayout() {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int heightDifference = screenHeight - (rect.bottom - rect.top);
        boolean isKeyboardShowing = heightDifference>screenHeight/3;   // 判断当前软键盘是否是显示的
        if ((isKeyboardShowing&&!mIsSoftKeyboardShowing) || (!isKeyboardShowing&&mIsSoftKeyboardShowing)){
            mIsSoftKeyboardShowing = isKeyboardShowing;
            onSoftKeyboardStateChangedListener.OnSoftKeyboardStateChanged(mIsSoftKeyboardShowing,heightDifference, screenHeight);
        }
    }
}
