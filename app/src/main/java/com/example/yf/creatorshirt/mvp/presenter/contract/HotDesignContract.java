package com.example.yf.creatorshirt.mvp.presenter.contract;

import com.example.yf.creatorshirt.mvp.model.HotDesignsBean;
import com.example.yf.creatorshirt.mvp.presenter.base.BasePresenter;
import com.example.yf.creatorshirt.mvp.view.BaseView;

import java.util.List;

/**
 * Created by yang on 31/07/2017.
 */

public interface HotDesignContract {
    interface HotDesignView extends BaseView {
        void showSuccess(List<HotDesignsBean.HotDesign> hotDesigns);
    }

    interface Presenter extends BasePresenter<HotDesignView> {
        void getHotDesign();
    }

}