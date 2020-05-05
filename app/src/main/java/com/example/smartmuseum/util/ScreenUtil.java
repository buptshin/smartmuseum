package com.example.smartmuseum.util;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class ScreenUtil {

    /**
     * 隐藏状态栏
     */
    public static void hideStatusBar(Activity activity){
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        Window window = activity.getWindow();
        window.setFlags(flag, flag);
    }

    /**
     * 设置状态栏文字颜色为黑色
     */
    public static void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
}
