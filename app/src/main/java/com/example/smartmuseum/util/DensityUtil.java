package com.example.smartmuseum.util;

import android.content.Context;

public class DensityUtil {
    /**
     * dp转px
     * */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
