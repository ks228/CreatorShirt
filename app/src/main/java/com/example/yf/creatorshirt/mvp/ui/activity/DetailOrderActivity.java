package com.example.yf.creatorshirt.mvp.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.example.yf.creatorshirt.R;

import butterknife.BindView;

public class DetailOrderActivity extends BaseActivity {

    @Override
    protected void inject() {

    }

    @Override
    protected void initView() {
        mAppBarTitle.setText("订单详情");
        mAppBarBack.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getView() {
        return R.layout.activity_detail_order;
    }
}
