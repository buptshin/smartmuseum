package com.example.smartmuseum.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartmuseum.model.Address;
import com.example.smartmuseum.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserViewModel extends ViewModel {
    private MutableLiveData<User> getAddressUserModel;

    //获取用户地址
    public MutableLiveData<User> getAddressUserModel(HashMap map) {
        if (getAddressUserModel == null) {
            getAddressUserModel = new MutableLiveData<>();
            loadAddressUserModel(map);
        }
        return getAddressUserModel;
    }

    public void loadAddressUserModel(HashMap map){
        Address address1 = new Address();
        address1.setLocationAddress("东1门-安检处");
        address1.setDetailAddress("中国国家博物馆东1门西侧");
        address1.setDefaultFlag(true);

        Address address2 = new Address();
        address2.setLocationAddress("文创商店自取");
        address2.setDetailAddress("中国国家博物馆3层近2号中央展厅");
        address2.setDefaultFlag(false);

        Address address3 = new Address();
        address3.setLocationAddress("北门");
        address3.setDetailAddress("中国国家博物馆北门内");
        address3.setDefaultFlag(false);

        Address address4 = new Address();
        address4.setLocationAddress("西门-游客集散中心");
        address4.setDetailAddress("中国国家博物馆西门游客集散中心");
        address4.setDefaultFlag(false);

        List<Address> addressList = new ArrayList<>();
        addressList.add(address1);
        addressList.add(address2);
        addressList.add(address3);
        addressList.add(address4);
//        addressList.add(address1);
//        addressList.add(address2);
//        addressList.add(address3);
//        addressList.add(address4);

        User user = new User();
        user.setAddress(addressList);

        getAddressUserModel.setValue(user);

    }
}
