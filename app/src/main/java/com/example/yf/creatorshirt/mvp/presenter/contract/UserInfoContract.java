package com.example.yf.creatorshirt.mvp.presenter.contract;

import com.example.yf.creatorshirt.mvp.model.LoginBean;
import com.example.yf.creatorshirt.mvp.presenter.base.BasePresenter;
import com.example.yf.creatorshirt.mvp.view.BaseView;

/**
 * Created by yang on 28/07/2017.
 */

public interface UserInfoContract {

    interface UserView extends BaseView {

        void showUserInfo(LoginBean userInfo);
    }

    interface Presenter extends BasePresenter<UserView> {
        //获取用户信息
        void getUserInfo(String token);
    }

}
