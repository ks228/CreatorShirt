package com.example.yf.creatorshirt.mvp.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yf.creatorshirt.R;
import com.example.yf.creatorshirt.mvp.ui.fragment.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/7/30.
 */

public class HotDesignsFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @Override
    protected void initInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bomb_styles;
    }

    @Override
    protected void initViews(View mView) {

    }

    @Override
    protected void initData() {

    }
}