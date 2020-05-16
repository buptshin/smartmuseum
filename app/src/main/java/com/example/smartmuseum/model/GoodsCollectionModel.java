package com.example.smartmuseum.model;

//文创和藏品绑定
public class GoodsCollectionModel {
    private Goods goods;
    private Collection collection;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}
