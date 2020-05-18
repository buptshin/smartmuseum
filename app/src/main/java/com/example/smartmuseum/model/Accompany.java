package com.example.smartmuseum.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.example.smartmuseum.BR;

/**
 * 用于展示同行伙伴的类
 */
public class Accompany extends BaseObservable {

    private String name;   // 同行者的姓名
    private int img;    // 同行者的头像
    private Boolean isInMuseum;  //同行者是否正在博物馆参观
    private Boolean isAdded;     //是否已添加为“个人伙伴”
    private int accompanyCount;  //同行的次数

    public Accompany(String name, int img, Boolean isInMuseum, Boolean isAdded, int accompanyCount) {
        this.name = name;
        this.img = img;
        this.isInMuseum = isInMuseum;
        this.isAdded = isAdded;
        this.accompanyCount = accompanyCount;
    }

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public int getImg() {
        return img;
    }

    @Bindable
    public Boolean getInMuseum() {
        return isInMuseum;
    }

    @Bindable
    public Boolean getAdded() {
        return isAdded;
    }

    @Bindable
    public int getAccompanyCount() {
        return accompanyCount;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public void setImg(int img) {
        this.img = img;
        notifyPropertyChanged(BR.img);
    }

    public void setInMuseum(Boolean inMuseum) {
        this.isInMuseum = inMuseum;
        notifyPropertyChanged(BR.inMuseum);
    }

    public void setAdded(Boolean added) {
        this.isAdded = added;
        notifyPropertyChanged(BR.added);
    }

    public void setAccompanyCount(int accompanyCount) {
        this.accompanyCount = accompanyCount;
        notifyPropertyChanged(BR.accompanyCount);
    }
}
