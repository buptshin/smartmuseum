package com.example.smartmuseum.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartmuseum.R;
import com.example.smartmuseum.model.Exhibition;
import com.example.smartmuseum.model.Goods;
import com.example.smartmuseum.util.http.HttpRequest;
import com.example.smartmuseum.util.http.HttpResult;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableCache;

public class ExhibitionViewModel extends ViewModel {
    // 展厅列表（展厅详细页展示）
    private MutableLiveData<List<Exhibition>> recommended_exhibition_list;
    //最新展览列表
    private MutableLiveData<List<Exhibition>> latest_exhibition_list;

    //获取推荐展览
    public MutableLiveData<List<Exhibition>> getRecommended_exhibition_list(HashMap map) {
        if (recommended_exhibition_list == null) {
            recommended_exhibition_list = new MutableLiveData<>();
            loadRecommendedExhibitionModelList(map);
//            // 获取展览的数据
//            List<Exhibition> exhibitions = new ArrayList<>();
//            exhibition_list.setValue(exhibitions);
        }
        return recommended_exhibition_list;
    }

    //获取最新展览
    public MutableLiveData<List<Exhibition>> getLatest_exhibition_list(HashMap map){
        if(latest_exhibition_list == null){
            latest_exhibition_list = new MutableLiveData<>();
            loadLatestExhibitionModelList(map);
        }
        return latest_exhibition_list;
    }


    //通过网络请求（假的）加载最新展览数据
    private void loadLatestExhibitionModelList(HashMap map) {


        Exhibition exhibition1 = new Exhibition("古代中国", R.drawable.mainpage_ancient_china, "基本陈列（常设）", 293993924,
                R.mipmap.mainpage_exhibition_like_not_selected, "地下一层展厅", 3.5, 240);
        Exhibition exhibition2 = new Exhibition("回归之路", R.drawable.mainpage_return_road, "2019.9.17-11.27", 223904,
                R.mipmap.mainpage_exhibition_like_selected, "北二，北三展厅", 1.5, 130);

        List<Exhibition> exhibitionList = new ArrayList<>();
        exhibitionList.add(exhibition2);
        exhibitionList.add(exhibition1);

        // 传入
        latest_exhibition_list.setValue(exhibitionList);



    }
    //通过网络请求（假的）加载推荐展览
    public void loadRecommendedExhibitionModelList(HashMap map){ //传入hashmap来接收网络请求数据
        Exhibition exhibition1 = new Exhibition("古代中国", R.drawable.mainpage_ancient_china, "基本陈列（常设）", 293993924,
                R.mipmap.mainpage_exhibition_like_not_selected, "地下一层展厅", 3.5, 240);

        Exhibition exhibition2 = new Exhibition("回归之路", R.drawable.mainpage_return_road, "2019.9.17-11.27", 223904,
                R.mipmap.mainpage_exhibition_like_selected, "北二，北三展厅", 1.5, 130);

        List<Exhibition> list = new ArrayList<>();
        list.add(exhibition1);
        list.add(exhibition2);

        recommended_exhibition_list.setValue(list);

//        //发送网络连接
//        HttpRequest.Get.getRecommendedExhibition(new Observer<HttpResult<List<Exhibition>>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(HttpResult<List<Exhibition>> listHttpResult) {
//
//                if(listHttpResult.getData() != null && listHttpResult.getCode() == 200){
//                    recommended_exhibition_list.setValue(listHttpResult.getData());
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        }, map);
    }



}
