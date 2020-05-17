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
    // 展厅列表（展厅详细页展示）
    private MutableLiveData<List<Exhibition>> exhibition_list;

    // 获取展览信息
    public MutableLiveData<List<Exhibition>> getExhibition_list(HashMap map) {
        if (exhibition_list == null) {
            exhibition_list = new MutableLiveData<>();
            loadExhibitionModelList(map);
//            // 获取展览的数据
//            List<Exhibition> exhibitions = new ArrayList<>();
//            exhibition_list.setValue(exhibitions);
        }
        return exhibition_list;
    }

    // 展厅详细页展示
    private void loadExhibitionModelList(HashMap map) {
        Exhibition exhibition1 = new Exhibition();
        exhibition1.setExhibition_name("回归之路");
        exhibition1.setExhibition_hall("北一、北三展厅");

        Exhibition exhibition2 = new Exhibition();
        exhibition2.setExhibition_name("古代中国");
        exhibition2.setExhibition_hall("地下一层展厅");

        List<Exhibition> exhibitionList = new ArrayList<>();
        exhibitionList.add(exhibition1);
        exhibitionList.add(exhibition2);
        // 传入
        exhibition_list.setValue(exhibitionList);
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
