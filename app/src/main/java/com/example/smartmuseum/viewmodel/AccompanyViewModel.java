package com.example.smartmuseum.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartmuseum.model.Accompany;
import com.example.smartmuseum.util.http.HttpRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    /**
     * 进行网络请求
     * @param map
     */
    public void loadAccompanyList(HashMap map) {
        List<Accompany> list = new ArrayList<>();
        list.add(new Accompany("路小璐",-700170,true,false,1));
        list.add(new Accompany("柳菲菲",-700168,true,false,0));
        list.add(new Accompany("韩馨月",-700167,true,true,0));
        list.add(new Accompany("马尚莱",-700165,false,false,0));
        list.add(new Accompany("周文韬",-700164,false,false,0));
        accompanyList.setValue(list);
    }
}
