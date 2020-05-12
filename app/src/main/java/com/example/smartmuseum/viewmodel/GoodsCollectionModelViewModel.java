package com.example.smartmuseum.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartmuseum.model.Collection;
import com.example.smartmuseum.model.Goods;
import com.example.smartmuseum.model.GoodsCollectionModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GoodsCollectionModelViewModel extends ViewModel {
    private MutableLiveData<List<GoodsCollectionModel>> signInGoodsCollectionModel;

    public MutableLiveData<List<GoodsCollectionModel>> getSignInGoodsCollectionModelList(HashMap map) {
        if (signInGoodsCollectionModel == null) {
            signInGoodsCollectionModel = new MutableLiveData<>();
            loadSignInGoodsCollectionModelList(map);
        }
        return signInGoodsCollectionModel;
    }

    public void loadSignInGoodsCollectionModelList(HashMap map){
        Collection collection1 = new Collection();
        collection1.setName("匽侯盂");
        collection1.setDynasty("西周");
        collection1.setExhibition("国家博物馆精品文物展");
        collection1.setIntroduction("推定燕国始封之地重要依据");

        Collection collection2 = new Collection();
        collection2.setName("霁青金彩河清尊");
        collection2.setDynasty("清");
        collection2.setExhibition("国家博物馆精品文物展");
        collection2.setIntroduction("圆明园海晏堂烧毁前陈列品");


        Goods goods1 = new Goods();
        goods1.setName("月光宝盒茶具");
        goods1.setIntroduction("匽侯盂同款花纹");
        goods1.setPrice(820);
        goods1.setAdvantage("手工高白泥");

        Goods goods2 = new Goods();
        goods2.setName("年年有鱼手镯");
        goods2.setIntroduction("霁青金彩河清尊同款莲花花纹");
        goods2.setPrice(599);
        goods2.setAdvantage("古法手工打磨");

        GoodsCollectionModel collectionModel1 = new GoodsCollectionModel();
        collectionModel1.setGoods(goods1);
        collectionModel1.setCollection(collection1);


        GoodsCollectionModel collectionModel2 = new GoodsCollectionModel();
        collectionModel2.setGoods(goods2);
        collectionModel2.setCollection(collection2);


        List<GoodsCollectionModel> list = new ArrayList<>();
        list.add(collectionModel1);
        list.add(collectionModel2);

        signInGoodsCollectionModel.setValue(list);
    }
}
