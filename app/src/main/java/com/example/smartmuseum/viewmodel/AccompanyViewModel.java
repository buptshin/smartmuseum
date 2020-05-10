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
        //HttpRequest.Get.
    }
}
