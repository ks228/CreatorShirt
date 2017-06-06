package com.example.yf.creatorshirt.mvp.model.db;

import com.example.yf.creatorshirt.mvp.model.bean.GirlData;
import com.example.yf.creatorshirt.mvp.model.bean.NewsSummary;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yang on 17/05/2017.
 */

public interface RequestApi {
    //主服务器的baseUrl
    String HOST = "http://gank.io/api/";

    //抓取数据
    Flowable<NewsSummary> getNewsSummary();

    //妹纸
    @GET("data/福利/{size}/{page}")
    Flowable<GirlData> getPhotoList(
            @Path("size") int size,
            @Path("page") int page);
}