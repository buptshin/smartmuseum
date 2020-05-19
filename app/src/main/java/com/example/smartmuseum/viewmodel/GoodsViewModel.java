package com.example.smartmuseum.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartmuseum.model.Goods;
import com.example.smartmuseum.model.GoodsFeature;
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

    private MutableLiveData<List<Goods>> classicaldGoodsModelList;

    private MutableLiveData<List<Goods>> discountGoodsModelList;

    private MutableLiveData<Goods> goodsModelInfo;

    private MutableLiveData<Goods> goodsModelInfoDetail;

    private MutableLiveData<List<Goods>> buyGoodsModelList;

    private MutableLiveData<List<Goods>> routeCommendGoodsModelList;

    //获取推荐商品
    public MutableLiveData<List<Goods>> getCommendGoodsModelList(HashMap map) {
        if (commendGoodsModelList == null) {
            commendGoodsModelList = new MutableLiveData<>();
            loadCommendGoodsModelList(map);
        }
        return commendGoodsModelList;
    }

    //获取已购买商品
    public MutableLiveData<List<Goods>> getPurchasedGoodsModelList(HashMap map) {
        if (purchasedGoodsModelList == null) {
            purchasedGoodsModelList = new MutableLiveData<>();
            loadPurchasedGoodsModelList(map);
        }
        return purchasedGoodsModelList;
    }

    //获取经典商品
    public MutableLiveData<List<Goods>> getClassicalGoodsModelList(HashMap map) {
        if (classicaldGoodsModelList == null) {
            classicaldGoodsModelList = new MutableLiveData<>();
            loadClassicalGoodsModelList(map);
        }
        return classicaldGoodsModelList;
    }

    //获取折扣商品
    public MutableLiveData<List<Goods>> getDiscountGoodsModelList(HashMap map) {
        if (discountGoodsModelList == null) {
            discountGoodsModelList = new MutableLiveData<>();
            loadDiscountGoodsModelList(map);
        }
        return discountGoodsModelList;
    }

    //获取商品信息
    public MutableLiveData<Goods> getGoodsModelInfo(HashMap map) {
        if (goodsModelInfo == null) {
            goodsModelInfo = new MutableLiveData<>();
            loadGoodsModelInfo(map);
        }
        return goodsModelInfo;
    }

    //获取商品详细信息
    public MutableLiveData<Goods> goodsInfoDetailInfo(HashMap map) {
        if (goodsModelInfoDetail == null) {
            goodsModelInfoDetail = new MutableLiveData<>();
            loadGoodsInfoDetailInfo(map);
        }
        return goodsModelInfoDetail;
    }


    //获取文物特展推荐商品
    public MutableLiveData<List<Goods>> getbuyGoodsModelList(HashMap map) {
        if (buyGoodsModelList == null) {
            buyGoodsModelList = new MutableLiveData<>();
            loadbuyGoodsModelList(map);
        }
        return buyGoodsModelList;
    }

    //获取已购买商品信息(付款页面)
    public MutableLiveData<List<Goods>> getRouteCommendGoodsModelList(HashMap map) {
        if (routeCommendGoodsModelList == null) {
            routeCommendGoodsModelList = new MutableLiveData<>();
            loadRouteommendGoodsModelList(map);
        }
        return routeCommendGoodsModelList;
    }

    public void loadClassicalGoodsModelList(HashMap map) {
        Goods test1 = new Goods();
        test1.setName("和田玉首饰");
        test1.setIntroduction("龙凤呈祥项链");

        Goods test2 = new Goods();
        test2.setName("帝都创意");
        test2.setIntroduction("鱼骨扇");

        Goods test3 = new Goods();
        test3.setName("创意陶瓷茶具");

        List<Goods> list = new ArrayList<>();
        list.add(test1);
        list.add(test2);
        list.add(test3);

        classicaldGoodsModelList.setValue(list);

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

    public void loadDiscountGoodsModelList(HashMap map){
        Goods test1 = new Goods();
        test1.setName("杏林春燕创意首饰");
        test1.setIntroduction("粉彩吉林春燕纹瓶");
        test1.setPrice(261);
        test1.setDiscount(50);
        test1.setRemainderNum(10);


        Goods test2 = new Goods();
        test2.setName("大盂鼎马克杯");
        test2.setIntroduction("大盂鼎");
        test2.setPrice(109);
        test2.setDiscount(40);
        test2.setRemainderNum(42);

        List<Goods> list = new ArrayList<>();
        list.add(test1);
        list.add(test2);

        discountGoodsModelList.setValue(list);
    }

    public void loadGoodsModelInfo(HashMap map){
        Goods test1 = new Goods();
        test1.setName("大观园纸雕灯");
        test1.setPrice(598);
        test1.setWeight("625g");
        test1.setSpecifications("36cm*26cm*6cm");

        goodsModelInfo.setValue(test1);
    }

    public void loadGoodsInfoDetailInfo(HashMap map){
        Goods test1 = new Goods();
        test1.setName("大观园纸雕灯");
        test1.setIntroduction("现代剪纸艺术结合光影效果重现红楼梦经典场景");
        test1.setPurchasedNum(2);

        GoodsFeature goodsFeature1 = new GoodsFeature();
        goodsFeature1.setImageId("1");
        goodsFeature1.setTitle("[光影交错 浪漫缠绵]");
        goodsFeature1.setIntroduction("    将7层高透光纸片精巧叠加，尽显层叠交错之美。柔和灯光邂逅高透光雕刻纸片，组成一幅朦胧、温馨、浪漫的画面。");

        GoodsFeature goodsFeature2 = new GoodsFeature();
        goodsFeature2.setImageId("2");
        goodsFeature2.setTitle("[激光雕刻 精美细腻]");
        goodsFeature2.setIntroduction("    采用全新激光雕刻工艺，智障雕刻细致入微，光洁无瑕疵。");

        GoodsFeature goodsFeature3 = new GoodsFeature();
        goodsFeature3.setImageId("2");
        goodsFeature3.setTitle("[文物元素 还原场景]");
        goodsFeature3.setIntroduction("    细致还原文物故事场景，画面形象呼之欲出。透过画面，大观园的富丽堂皇和精致精巧扑面而来。细细观看仿佛可以听见流水潺潺、微风绵绵、宝玉和黛玉细雨轻快、娴静自得。");


        List<GoodsFeature> goodsFeatureList = new ArrayList<>();
        goodsFeatureList.add(goodsFeature1);
        goodsFeatureList.add(goodsFeature2);
        goodsFeatureList.add(goodsFeature3);

        test1.setFeature(goodsFeatureList);

        goodsModelInfoDetail.setValue(test1);
    }

    public void loadbuyGoodsModelList(HashMap map){
        Goods test1 = new Goods();
        test1.setName("月光宝盒陶瓷茶具");
        test1.setPrice(820);
        test1.setType("有礼盒");
        test1.setPurchasedNum(1);

        Goods test2 = new Goods();
        test2.setName("大观园纸雕灯");
        test2.setPrice(598);
        test2.setType("无礼盒");
        test2.setPurchasedNum(2);

        Goods test3 = new Goods();
        test3.setName("年年有鱼手镯 ");
        test3.setPrice(599);
        test3.setType("无礼盒");
        test3.setPurchasedNum(1);

        List<Goods> list = new ArrayList<>();
        list.add(test1);
        list.add(test2);
        list.add(test3);

        buyGoodsModelList.setValue(list);
    }


    public void loadRouteommendGoodsModelList(HashMap map) {
        Goods test1 = new Goods();
        test1.setCollectName("青铜匽侯盂");
        test1.setName("月光宝盒陶瓷茶具");

        Goods test2 = new Goods();
        test2.setCollectName("霁青金彩海晏河清尊");
        test2.setName("年年有鱼纯银手镯");

        List<Goods> list = new ArrayList<>();
        list.add(test1);
        list.add(test2);
        list.add(test1);
        list.add(test2);

        routeCommendGoodsModelList.setValue(list);
    }




}
