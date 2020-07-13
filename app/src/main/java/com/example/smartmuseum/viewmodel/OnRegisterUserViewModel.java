package com.example.smartmuseum.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartmuseum.model.OnRegisterUser;
import com.example.smartmuseum.model.User;
import com.example.smartmuseum.util.http.HttpRequest;
import com.example.smartmuseum.util.http.HttpResult;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class OnRegisterUserViewModel extends ViewModel {

    private MutableLiveData<OnRegisterUser> onRegisterUserInfo;

    public MutableLiveData<OnRegisterUser> getOnRegisterUserInfo() {
        if (onRegisterUserInfo == null)
            onRegisterUserInfo = new MutableLiveData<>();
        return onRegisterUserInfo;
    }

    public void registerUser(HashMap map){
        map.put("registerUserInfo",onRegisterUserInfo.getValue());
        HttpRequest.Post.registerUser(new Observer<HttpResult<User>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HttpResult<User> userHttpResult) {
                // 得到返回的用户信息
                if (userHttpResult.getCode() == 200)
                    Log.d("register","您已注册完成");
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
