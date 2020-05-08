package com.example.smartmuseum.util.http;

import com.example.smartmuseum.model.Goods;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public class HttpRequest {
    public interface TestService {
        @POST
        Observable<HttpResult<Goods>> testForPost(@Url String url, @Body RequestBody route);
        @POST
        Observable<HttpResult<Goods>> testForPost2(@Url String url, @Header("token") String token, @Body RequestBody route);
        @GET("getGoodsInfo")
        Observable<HttpResult<List<Goods>>> testGet(@Header("token") String token, @Query("testId") String testId);
    }

    public static class Get {
        //测试GET
        public static void testGet(Observer<HttpResult<List<Goods>>> observer, HashMap<String, String> map) {
            TestService testService = RetrofitWrapper.getInstance().getRetrofit().create(TestService.class);
            String token = map.get("token");
            String testId = map.get("testid");

            testService.testGet(token, testId)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    public static class Post {

        //测试POST(不带header)
        public static void testForPost(Observer<HttpResult<Goods>> observer, HashMap<String, String> map) {
            TestService testService = RetrofitWrapper.getInstance().getRetrofit().create(TestService.class);

            String json = new Gson().toJson(map);//要传递的json

            RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
            testService.testForPost("user/login", requestBody)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }

        //测试POST(带header)
        public static void testForPost2(Observer<HttpResult<Goods>> observer, String token, HashMap map){
            TestService testService = RetrofitWrapper.getInstance().getRetrofit().create(TestService.class);

            String json= new Gson().toJson(map);//要传递的json

            RequestBody requestBody=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
            testService.testForPost2("test/postgoods/info", token, requestBody)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }
}
