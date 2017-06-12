package com.example.yf.creatorshirt.mvp.model.db;

import com.example.yf.creatorshirt.mvp.model.bean.GirlData;
import com.example.yf.creatorshirt.mvp.model.bean.LoginBean;
import com.example.yf.creatorshirt.mvp.model.bean.NewsSummary;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by yang on 17/05/2017.
 * Retrofit2获取数据辅助类
 */

public class RetrofitHelper implements HttpHelper {
    private RequestApi mRequestApi;

    /**
     * Dagger2直接注入对象
     *
     * @param requestApiService
     */
    @Inject
    public RetrofitHelper(RequestApi requestApiService) {
        mRequestApi = requestApiService;
    }

    /**
     * RxJava2抓取数据
     *
     * @return
     */
    @Override
    public Flowable<NewsSummary> getDataNewsSummary() {
        return mRequestApi.getNewsSummary();
    }

    /**
     * 获取照片
     *
     * @param size
     * @param page
     * @return
     */
    public Flowable<GirlData> getPhotoList(int size, int page) {
        return mRequestApi.getPhotoList(size, page);
    }

    /**
     * phone login
     *
     * @param phone
     * @param code
     * @return
     */
    @Override
    public Observable<LoginBean> login(String phone, String code) {
        return mRequestApi.loginPhone(phone, code);
    }

    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    @Override
    public Observable<LoginBean> getVerifyCode(String phone) {
        return mRequestApi.getCode(phone);
    }

}
