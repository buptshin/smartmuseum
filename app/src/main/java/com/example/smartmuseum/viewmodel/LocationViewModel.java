package com.example.smartmuseum.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartmuseum.model.Location;
import com.example.smartmuseum.util.http.HttpRequest;
import com.example.smartmuseum.util.http.HttpResult;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LocationViewModel extends ViewModel {
    private MutableLiveData<Location> nowLocationModel;

    //获取当前位置
    public MutableLiveData<Location> getNowLocationModel(HashMap map) {
        if (nowLocationModel == null) {
            nowLocationModel = new MutableLiveData<>();
            loadNowLocationModel(map);
        }
        return nowLocationModel;
    }

    public void loadNowLocationModel(HashMap map) {
        //发送网络连接
        HttpRequest.Get.getNowLocation(new Observer<HttpResult<Location>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HttpResult<Location> httpResult) {

                if (httpResult.getData() != null && httpResult.getCode() == 200) {
                    nowLocationModel.setValue(httpResult.getData());
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
