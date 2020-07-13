package com.example.smartmuseum.model;

import com.example.smartmuseum.util.http.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 正在注册的用户类
 */
public class OnRegisterUser {
    private String userName;
    private String nickName;
    private String userTel;
    private String userPwd;
    private int userGender;  // 1——MALE;  2——FEMALE
    private List<String> userPermissions = new ArrayList<>();

    public OnRegisterUser(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public int getUserGender() {
        return userGender;
    }

    public void setUserGender(int userGender) {
        this.userGender = userGender;
    }

    public List<String> getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(List<String> userPermissions) {
        this.userPermissions = userPermissions;
    }

    @Override
    public String toString() {
        return "OnRegisterUser{" +
                "userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userGender=" + userGender +
                ", userPermissions=" + userPermissions +
                '}';
    }
}
