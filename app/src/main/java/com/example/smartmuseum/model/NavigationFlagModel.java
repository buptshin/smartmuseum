package com.example.smartmuseum.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NavigationFlagModel extends ViewModel {

    /** 标记筛选按钮是否打开*/
    private MutableLiveData<Boolean> isGreen;

    /*
     * 标记当前地图放大倍数，默认0，最大3
     * 0：楼层导览
     * 1：当前楼层地图
     * 2：当前楼层地图放大模式
     * 3：文物导览模式
     * */
    private MutableLiveData<Integer> zoomValue;

    public NavigationFlagModel() {
        if (isGreen == null){
            isGreen = new MutableLiveData<>();
            isGreen.setValue(false);
        }
        if (zoomValue == null){
            zoomValue = new MutableLiveData<>();
            zoomValue.setValue(0);
        }
    }

    public MutableLiveData<Boolean> getIsGreen() {
        if (isGreen == null){
            isGreen = new MutableLiveData<>();
            isGreen.setValue(false);
        }
        return isGreen;
    }

    public MutableLiveData<Integer> getZoomValue() {
        if (zoomValue == null){
            zoomValue = new MutableLiveData<>();
            zoomValue.setValue(0);
        }
        return zoomValue;
    }

    public void changeIsGreen(){
        if (isGreen.getValue() == true && zoomValue.getValue() != 1){
            isGreen.setValue(false);
        }else if (isGreen.getValue() == false && zoomValue.getValue() != 1){
            isGreen.setValue(true);
        }
    }

    //强制将筛选状态恢复到默认值false
    public void setIsGreenToNormal(){
        isGreen.setValue(false);
    }

    public void addZoomValue(){
        if (zoomValue.getValue() == 0 && isGreen.getValue() == true){
            zoomValue.setValue(zoomValue.getValue() + 1);
        }else if (zoomValue.getValue() < 2 && zoomValue.getValue() != 0){
            zoomValue.setValue(zoomValue.getValue() + 1);
        }
    }

    public void lessZoomValue(){
        if (zoomValue.getValue() >= 0) {
            zoomValue.setValue(zoomValue.getValue() - 1);
        }
    }
}
