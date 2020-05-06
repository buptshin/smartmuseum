package com.example.smartmuseum.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartmuseum.model.Accompany;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理“添加伙伴”界面的数据信息
 */
public class AccompanyViewModel extends ViewModel {
    private MutableLiveData<List<Accompany>> accompanyList;

    public MutableLiveData<List<Accompany>> getAccompanyList() {
        if(accompanyList == null){
            accompanyList = new MutableLiveData<>();
            /*
            在这里进行列表的初始化工作（从数据库得到同行伙伴的信息）
             */
            List<Accompany> tempAccompany = new ArrayList<>();
            accompanyList.setValue(tempAccompany);
        }
        return accompanyList;
    }
}
