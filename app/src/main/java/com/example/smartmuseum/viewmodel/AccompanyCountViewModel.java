package com.example.smartmuseum.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccompanyCountViewModel extends ViewModel {

    private MutableLiveData<Integer> account;

    public MutableLiveData<Integer> getAccount() {
        if(account == null){
            account = new MutableLiveData<Integer>();
            account.setValue(1);  // 同行伙伴的数量默认是一位
        }
        return account;
    }

    public void addAccompany(Integer num){
        account.setValue(account.getValue()+num);
    }
}
