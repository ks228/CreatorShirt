package com.example.yf.creatorshirt.mvp.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.yf.creatorshirt.R;
import com.example.yf.creatorshirt.common.UserInfoManager;
import com.example.yf.creatorshirt.mvp.model.LoginBean;
import com.example.yf.creatorshirt.mvp.presenter.UserInfoPresenter;
import com.example.yf.creatorshirt.mvp.presenter.contract.UserInfoContract;
import com.example.yf.creatorshirt.mvp.ui.activity.AddressShowActivity;
import com.example.yf.creatorshirt.mvp.ui.activity.AllOrderActivity;
import com.example.yf.creatorshirt.mvp.ui.activity.UserCenterActivity;
import com.example.yf.creatorshirt.mvp.ui.fragment.base.BaseFragment;
import com.example.yf.creatorshirt.utils.ToastUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by panguso on 2017/5/11.
 */

public class MineFragment extends BaseFragment<UserInfoPresenter> implements UserInfoContract.UserView {

    @BindView(R.id.user_avatar)
    ImageView mUserPicture;
    @BindView(R.id.choice_address_iv)
    ImageView mChoiceAddress;
    @BindView(R.id.choice_design_iv)
    ImageView mChoiceDesign;
    @BindView(R.id.choice_order_iv)
    ImageView mChoiceOrder;
    @BindView(R.id.design_number)
    TextView mDesignNumber;
    @BindView(R.id.order_number)
    TextView mOrderNumber;
    @BindView(R.id.address_number)
    TextView mAddressNumber;
    @BindView(R.id.user_name)
    TextView mUserName;

    private LoginBean mUserInfo;

    @Inject
    Activity mActivity;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View mView) {

    }

    @Override
    protected void initData() {
//        //根据第一次登录的接口返回的Token去访问用户返回的信息
//        if (UserInfoManager.getInstance().getLoginResponse() != null) {
//            mPresenter.getUserInfo(UserInfoManager.getInstance().getLoginResponse().getToken());
//        }

    }

    @OnClick({R.id.user_avatar, R.id.choice_address_iv, R.id.choice_order_iv, R.id.choice_design_iv,
            R.id.order_number, R.id.address_number, R.id.design_number})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_avatar:
                startCommonActivity(mActivity, null, UserCenterActivity.class);
                break;
            case R.id.choice_address_iv:
            case R.id.address_number:
                startCommonActivity(mActivity, null, AddressShowActivity.class);
                break;
            case R.id.choice_order_iv:
            case R.id.order_number:
                startCommonActivity(mActivity, null, AllOrderActivity.class);
                break;
            case R.id.design_number:
            case R.id.choice_design_iv:
                Bundle bundle = new Bundle();
                bundle.putString("title", "我的设计");
                startCommonActivity(mActivity, bundle, AllOrderActivity.class);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(UserInfoManager.getInstance().getLoginResponse() != null){
            mPresenter.getUserInfo(UserInfoManager.getInstance().getLoginResponse().getToken());
        }
    }

    @Override
    public void showErrorMsg(String msg) {
        ToastUtil.showToast(mActivity, msg, 0);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showUserInfo(LoginBean userInfo) {
        mUserInfo = userInfo;
        updateUserView();
    }

    //更新视图
    public void updateUserView() {
        mUserName.setText(mUserInfo.getUserInfo().getName());
        RequestOptions options = new RequestOptions()
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.mm);
        Glide.with(mActivity).
                load(mUserInfo.getUserInfo().getHeadImage()).apply(options).into(mUserPicture);
    }
}
