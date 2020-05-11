package com.example.smartmuseum.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartmuseum.model.Goods;
import com.example.smartmuseum.util.http.HttpRequest;
import com.example.smartmuseum.util.http.HttpResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class GoodsViewModel extends ViewModel {

    private MutableLiveData<List<Goods>> commendGoodsModelList;

    private MutableLiveData<List<Goods>> purchasedGoodsModelList;

    public MutableLiveData<List<Goods>> getCommendGoodsModelList(HashMap map) {
        if (commendGoodsModelList == null) {
            commendGoodsModelList = new MutableLiveData<>();
            loadCommendGoodsModelList(map);
        }
        return commendGoodsModelList;
    }

    public MutableLiveData<List<Goods>> getPurchasedGoodsModelList(HashMap map) {
        if (purchasedGoodsModelList == null) {
            purchasedGoodsModelList = new MutableLiveData<>();
            loadPurchasedGoodsModelList(map);
        }
        return purchasedGoodsModelList;
    }

    public void loadCommendGoodsModelList(HashMap map){
        Goods test1 = new Goods();
        test1.setCollectName("青铜匽侯盂");
        test1.setName("月光宝盒陶瓷茶具");
        test1.setIntroduction("匽侯盂同款花纹与手工高白泥的碰撞");
        test1.setPrice(820);
        test1.setOldPrice(999);
        test1.setBuyPeopleNum(120);

        Goods test2 = new Goods();
        test2.setCollectName("霁青金彩海晏河清尊");
        test2.setName("年年有鱼纯银手镯");
        test2.setIntroduction("采用莲花纹 古法手工打磨");
        test2.setPrice(599);
        test2.setOldPrice(999);
        test2.setBuyPeopleNum(120);

        List<Goods> list = new ArrayList<>();
        list.add(test1);
        list.add(test2);

        commendGoodsModelList.setValue(list);


        //发送网络连接
//        HttpRequest.Get.testGet(new Observer<HttpResult<List<Goods>>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(HttpResult<List<Goods>> httpResult) {
//
//                if (httpResult.getData() != null) {
//                    goodsModelList.setValue(httpResult.getData());
//                }
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        },map);
    }

    public void loadPurchasedGoodsModelList(HashMap map){
        Goods test1 = new Goods();
        test1.setName("月光宝盒陶瓷茶具");
        test1.setPrice(820);
        test1.setOldPrice(999);
        test1.setPurchasedNum(1);
        test1.setPurchasedStatus("配送中");

        Goods test2 = new Goods();
        test2.setName("年年有鱼纯银手镯");
        test2.setPrice(599);
        test2.setOldPrice(999);
        test2.setPurchasedNum(1);
        test2.setPurchasedStatus("取货中");

        List<Goods> list = new ArrayList<>();
        list.add(test1);
        list.add(test2);
        list.add(test2);

        purchasedGoodsModelList.setValue(list);
    }
}
