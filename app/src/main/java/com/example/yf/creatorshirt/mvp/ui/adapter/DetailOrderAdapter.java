package com.example.yf.creatorshirt.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.yf.creatorshirt.R;
import com.example.yf.creatorshirt.mvp.model.orders.ClothesSize;
import com.example.yf.creatorshirt.mvp.ui.adapter.base.BaseAdapter;
import com.example.yf.creatorshirt.mvp.ui.adapter.viewholder.DetailOrderHolder;

/**
 * Created by yangfang on 2017/12/17.
 */

public class DetailOrderAdapter extends BaseAdapter<ClothesSize, DetailOrderHolder> {


    public DetailOrderAdapter(Context context) {
        super(context);
    }

    @Override
    protected DetailOrderHolder createItemViewHolder(ViewGroup parent, int viewType) {
        return new DetailOrderHolder(parent, R.layout.item_detail_order);
    }

    @Override
    protected void bindCustomViewHolder(final DetailOrderHolder holder, final int position) {
        holder.mSizeNumber.setText(mData.get(position).getSize());
        holder.mSizeLetter.setText(mData.get(position).getLetter());
        holder.mClothesNumber.setText("已选："+mData.get(position).getCount());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(null,position,mData.get(position));
            }
        });
    }

}