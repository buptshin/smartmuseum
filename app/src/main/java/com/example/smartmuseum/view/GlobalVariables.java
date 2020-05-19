package com.example.smartmuseum.view;

public class GlobalVariables {
    public static int location_change = 0;  // 全局变量设置user是否移动（0为没有移动，移动后变为1）

    public static boolean hasAcompany = false;

    public int getLocation_change() {
        return location_change;
    }

    public void setLocation_change(int location_change) {
        this.location_change = location_change;
    }

}
