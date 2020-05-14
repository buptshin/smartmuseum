package com.example.smartmuseum.model;

import com.google.gson.annotations.SerializedName;

public class Goods {
    //名称
    private String name;

    //相关展厅名称
    @SerializedName("placeNamessssss")
    private String placeName;

    //介jie绍
    private String introduce;

    //购买人数
    private int buyPeopleNum;

    //价格
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getBuyPeopleNum() {
        return buyPeopleNum;
    }

    public void setBuyPeopleNum(int buyPeopleNum) {
        this.buyPeopleNum = buyPeopleNum;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
