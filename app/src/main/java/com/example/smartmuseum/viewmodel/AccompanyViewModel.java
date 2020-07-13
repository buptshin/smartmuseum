package com.example.smartmuseum.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartmuseum.model.Accompany;
import com.example.smartmuseum.util.http.HttpRequest;
import com.example.smartmuseum.util.http.HttpResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 管理“添加伙伴”界面的数据信息
 */
public class AccompanyViewModel extends ViewModel {
    private MutableLiveData<List<Accompany>> accompanyList;

    public MutableLiveData<List<Accompany>> getAccompanyList(HashMap map) {
        if(accompanyList == null){
            accompanyList = new MutableLiveData<>();
            loadAccompanyList(map);
        }
        return accompanyList;
    }

    public void loadAccompanyList(HashMap map) {
//        List<Accompany> list = new ArrayList<>();
//        list.add(new Accompany("路小璐",-700170,true,false,1));
//        list.add(new Accompany("柳菲菲",-700168,true,false,0));
//        list.add(new Accompany("韩馨月",-700167,true,true,0));
//        list.add(new Accompany("马尚莱",-700165,false,false,0));
//        list.add(new Accompany("周文韬",-700164,false,false,0));
//        accompanyList.setValue(list);


        HttpRequest.Get.getAccompany(new Observer<HttpResult<List<Accompany>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HttpResult<List<Accompany>> listHttpResult) {
                if (listHttpResult.getData()!=null && listHttpResult.getCode()==200)
                    accompanyList.setValue(listHttpResult.getData());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        },map);
    }

    // 更新同行者的信息
    public void upDateAccompany(HashMap map){
        map.put("accompany",accompanyList.getValue());
        HttpRequest.Post.upDateAccompany(new Observer<HttpResult<Object>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HttpResult<Object> objectHttpResult) {
                // 这里干啥
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        },"token" ,map);
    }
}
