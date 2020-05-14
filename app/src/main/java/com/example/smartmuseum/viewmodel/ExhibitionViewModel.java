package com.example.smartmuseum.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartmuseum.model.Exhibition;
import com.example.smartmuseum.model.Goods;
import com.example.smartmuseum.util.http.HttpRequest;
import com.example.smartmuseum.util.http.HttpResult;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableCache;

public class ExhibitionViewModel extends ViewModel {
    private MutableLiveData<List<Exhibition>> exhibition_list;

    public MutableLiveData<List<Exhibition>> getExhibition_list(HashMap map) {
        if (exhibition_list == null) {
            exhibition_list = new MutableLiveData<>();
            loadExhibitionModelList(map);
            // 获取展览的数据
            List<Exhibition> exhibitions = new ArrayList<>();
            exhibition_list.setValue(exhibitions);
        }
        return exhibition_list;
    }

    private void loadExhibitionModelList(HashMap map) {
        // 发送网络请求获取
//        HttpRequest.Get.testGet(new Observer<HttpResult<List<Exhibtion>>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(HttpResult<List<Goods>> listHttpResult) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }
}
