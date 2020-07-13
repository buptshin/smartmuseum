package com.example.smartmuseum.util;

import androidx.fragment.app.Fragment;


/**
 * 对实际的定位信息接收进行懒加载的fragment，主要用于导览模块
 *     避免用户在界面都不打开的情况下就订阅“定位”，避免重复订阅
 */
public abstract class LazyLocationFragment extends Fragment {
    private boolean isFirstLoad = true;

    protected abstract void onSubscribe();
    protected abstract void cancelSubscribe();

    // 有的fragment可能需要每次在可见的时候都执行一些操作
    protected void additionalWork(){
        return;
    }

    @Override
    public void onResume() {
        super.onResume();
        additionalWork();
        if (isFirstLoad){
            onSubscribe();
            isFirstLoad = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        cancelSubscribe();
        isFirstLoad = true;
    }
}
