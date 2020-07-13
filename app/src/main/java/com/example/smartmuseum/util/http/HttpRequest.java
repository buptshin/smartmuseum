package com.example.smartmuseum.util.http;

import com.example.smartmuseum.model.Exhibition;
import com.example.smartmuseum.model.Accompany;
import com.example.smartmuseum.model.Goods;
import com.example.smartmuseum.model.Location;
import com.example.smartmuseum.model.OnRegisterUser;
import com.example.smartmuseum.model.User;
import com.example.smartmuseum.util.RegexUtils;
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
        @GET("goods/getGoodsInfo")
        Observable<HttpResult<List<Goods>>> testGet(@Header("token") String token, @Query("testId") String testId);
    }

    public interface RegisterService{
        @POST("register/registerUser")
        Observable<HttpResult<User>> registerUser(@Body RequestBody body);
    }

    public interface LoginService{
        @POST("login")
        Observable<HttpResult<User>> login(@Body RequestBody body);
    }

    public interface GoodsService {
        @GET("goods/getCommendGoodsInfo")
        Observable<HttpResult<List<Goods>>> getCommendGoods(@Header("token") String token, @Query("goodsId") String goodsId);
    }

    public interface LoacationService {
        @GET("location/getNowLocation")
        Observable<HttpResult<Location>> getNowLocation(@Header("token") String token, @Query("nowloaction") String location);
    }

    //探索页面-展览
    public interface ExhibitionService {
        @GET("exhibition/getRecommendedExhibitionInfo")
        Observable<HttpResult<List<Exhibition>>> getRecommendedExhibition(@Header("token") String token, @Query("exhibitionId") String exhibitionId);
    }

    public interface AccompanyService{
        @GET("accompany/getAccompany")
        Observable<HttpResult<List<Accompany>>> getAccompany(@Header("token") String token, @Query("userId") String userId);
        @POST("accompany/upDateAccompany")
        Observable<HttpResult<Object>> upDateAccompany(@Header("token")String token, @Body RequestBody accompanyList);
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

        /**
         * 位置获取
         */
        //获取推荐的文创商品
        public static void getNowLocation(Observer<HttpResult<Location>> observer, HashMap<String, String> map) {
            LoacationService loacationService = RetrofitWrapper.getInstance().getRetrofit().create(LoacationService.class);
            String token = map.get("token");
            String location = map.get("location");

            loacationService.getNowLocation(token, location)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }

        /**
         * 文创商品
         */
        //获取推荐的文创商品
        public static void getCommendGoods(Observer<HttpResult<List<Goods>>> observer, HashMap<String, String> map) {
            GoodsService goodsService = RetrofitWrapper.getInstance().getRetrofit().create(GoodsService.class);
            String token = map.get("token");
            String goodsId = map.get("goodsId");

            goodsService.getCommendGoods(token, goodsId)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }

        /**
         * 展览
         */
        //获取推荐展览
        public static void getRecommendedExhibition(Observer<HttpResult<List<Exhibition>>> observer, HashMap<String, String> map){
            ExhibitionService exhibitionService = RetrofitWrapper.getInstance().getRetrofit().create(ExhibitionService.class);
            String token = map.get("token");
            String exhibitionId = map.get("exhibitionId");

            exhibitionService.getRecommendedExhibition(token, exhibitionId)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

        /**
         * 同行伙伴获取
         */
        // 个人伙伴获取
        public static void getAccompany(Observer<HttpResult<List<Accompany>>> observer, HashMap<String, String> hashMap){
            AccompanyService accompanyService = RetrofitWrapper.getInstance().getRetrofit().create(AccompanyService.class);
            String token = hashMap.get("token");
            String userId = hashMap.get("userId");

            accompanyService.getAccompany(token,userId)
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
        // 登录
        public static void login(Observer<HttpResult<User>> observer, HashMap hashMap){
            LoginService loginService = RetrofitWrapper.getInstance().getRetrofit().create(LoginService.class);
            String data = new Gson().toJson(hashMap);
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),data);
            loginService.login(body)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }

        // 注册用户
        public static void registerUser(Observer<HttpResult<User>> observer,HashMap hashMap){
            RegisterService registerService = RetrofitWrapper.getInstance().getRetrofit().create(RegisterService.class);
            String data = new Gson().toJson(hashMap);
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),data);
            registerService.registerUser(body)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }

        // 更新同行伙伴的信息
        public static void upDateAccompany(Observer<HttpResult<Object>> observer, String token, HashMap hashMap){
            AccompanyService accompanyService = RetrofitWrapper.getInstance().getRetrofit().create(AccompanyService.class);
            String data = new Gson().toJson(hashMap);

            RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),data);
            accompanyService.upDateAccompany(token,requestBody)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }
}
