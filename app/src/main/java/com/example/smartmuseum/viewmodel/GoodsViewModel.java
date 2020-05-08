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

    private MutableLiveData<List<Goods>> goodsModelList;

    public MutableLiveData<List<Goods>> getGoodsModelList(HashMap map) {
        if (goodsModelList == null) {
            goodsModelList = new MutableLiveData<>();
            loadGoodsModelList(map);
        }
        return goodsModelList;
    }

    public void loadGoodsModelList(HashMap map){
        //发送网络连接
        HttpRequest.Get.testGet(new Observer<HttpResult<List<Goods>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HttpResult<List<Goods>> httpResult) {

                if (httpResult.getData() != null) {
                    goodsModelList.setValue(httpResult.getData());
                }

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        },map);
    }
}
