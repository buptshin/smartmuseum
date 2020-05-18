package com.example.smartmuseum.model;

//地址类
public class Address {
    //当前位置
    private String locationAddress;
    //详细地址
    private String detailAddress;

    //默认地址(值为true时是默认地址)
    private boolean defaultFlag;

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public boolean isDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
    }
}
