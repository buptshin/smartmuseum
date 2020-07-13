package com.example.smartmuseum.model;

import java.util.Map;

/**
 * 当前用户具体的定位信息类
 */
public class UserLocation {
    private String floor;  // 当前所在楼层
    private String exhibitionHall;  // 当前所在的展厅
    private float exhibitionX, exhibitionY; // 展厅内的相对X/Y
    private double latitude, longitude;  // 用户的经纬度（实际定位）

    private UserLocation() {
    }

    private static class SingletonHolder {
        private static final UserLocation sInstance = new UserLocation();
    }

    // 提供外部的修改函数
    public static UserLocation updateCurPos(Map<String, String> result) {
        UserLocation userLocation = SingletonHolder.sInstance;
        userLocation.floor = "***";
        userLocation.exhibitionHall = "北一展厅——复兴之路";
        userLocation.exhibitionX = Float.parseFloat(result.get("x"));
        userLocation.exhibitionY = Float.parseFloat(result.get("y"));
        userLocation.latitude = Double.parseDouble(result.get("latitude"));
        userLocation.longitude = Double.parseDouble(result.get("longitude"));
        return userLocation;
    }

    public String getFloor() {
        return floor;
    }

    public String getExhibitionHall() {
        return exhibitionHall;
    }

    public float getExhibitionX() {
        return exhibitionX;
    }

    public float getExhibitionY() {
        return exhibitionY;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
