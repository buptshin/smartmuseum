package com.example.smartmuseum.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

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

    //购买状态
    private String purchasedStatus;

    //购买数量
    private int purchasedNum;

    //促销剩余时间
    private String discountRemainderTime;

    //库存数量
    private int remainderNum;

    //促销力度(0-100)
    private int discount;

    //优点
    private String advantage;

    //重量
    private String weight;

    //产品规格
    private String specifications;

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

    public String getPurchasedStatus() {
        return purchasedStatus;
    }

    public void setPurchasedStatus(String purchasedStatus) {
        this.purchasedStatus = purchasedStatus;
    }

    public int getPurchasedNum() {
        return purchasedNum;
    }

    public void setPurchasedNum(int purchasedNum) {
        this.purchasedNum = purchasedNum;
    }

    public String getDiscountRemainderTime() {
        return discountRemainderTime;
    }

    public void setDiscountRemainderTime(String discountRemainderTime) {
        this.discountRemainderTime = discountRemainderTime;
    }

    public int getRemainderNum() {
        return remainderNum;
    }

    public void setRemainderNum(int remainderNum) {
        this.remainderNum = remainderNum;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }
}
