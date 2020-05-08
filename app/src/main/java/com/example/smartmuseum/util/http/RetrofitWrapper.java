package com.example.smartmuseum.util.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit类
 */
public class RetrofitWrapper {
    private static final int DEFAULT_TIMEOUT = 5;

    public static final String BASE_URL = "https://mock.yonyoucloud.com/mock/6297/guobo/";

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    private Retrofit retrofit;

    private RetrofitWrapper() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    private static class SingletonHolder{
        private static final RetrofitWrapper INSTANCE = new RetrofitWrapper();
    }

    public static RetrofitWrapper getInstance(){
        return SingletonHolder.INSTANCE;
    }


}
