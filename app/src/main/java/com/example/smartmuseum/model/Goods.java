package com.example.smartmuseum.model;

import com.google.gson.annotations.SerializedName;

public class Goods {
    //名称
    private String name;

    //相关文物名称
    private String collectName;

    //介绍
    private String introduction;

    //购买人数
    private int buyPeopleNum;

    //价格
    private int price;

    //旧价格
    private int oldPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollectName() {
        return collectName;
    }

    public void setCollectName(String collectName) {
        this.collectName = collectName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }
}
