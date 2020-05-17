package com.example.smartmuseum.model;

//文创商品特点
public class GoodsFeature {

    //图片Id
    private String imageId;
    //标题
    private String title;

    //介绍
    private String introduction;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
