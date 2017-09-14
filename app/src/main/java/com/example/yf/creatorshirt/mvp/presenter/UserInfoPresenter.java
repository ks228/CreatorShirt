package com.example.yf.creatorshirt.mvp.presenter;

import com.example.yf.creatorshirt.http.DataManager;
import com.example.yf.creatorshirt.http.HttpResponse;
import com.example.yf.creatorshirt.mvp.model.LoginBean;
import com.example.yf.creatorshirt.mvp.presenter.base.RxPresenter;
import com.example.yf.creatorshirt.mvp.presenter.contract.UserInfoContract;
import com.example.yf.creatorshirt.utils.RxUtils;
import com.example.yf.creatorshirt.widget.CommonObserver;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * Created by yang on 28/07/2017.
 */

public class UserInfoPresenter extends RxPresenter<UserInfoContract.UserView> implements UserInfoContract.Presenter {
    private DataManager manager;

    @Inject
    public UserInfoPresenter(DataManager manager) {
        this.manager = manager;
    }

    /**
     * 获取用户信息
     * @param token
     */
    @Override
    public void getUserInfo(String token) {
        addSubscribe(manager.getUserInfo(token)
                .compose(RxUtils.<HttpResponse<LoginBean>>rxObScheduleHelper())
                .compose(RxUtils.<LoginBean>handleObservableResult())
                .subscribeWith(new CommonObserver<LoginBean>(mView, "请求失败") {
                    @Override
                    public void onNext(@NonNull LoginBean userInfo) {
                        if (userInfo == null) {
                            return;
                        }
                        mView.showUserInfo(userInfo);
                    }
                }));

    }
}
