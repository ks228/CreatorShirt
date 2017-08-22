package com.example.yf.creatorshirt.mvp.ui.adapter.design;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.yf.creatorshirt.R;
import com.example.yf.creatorshirt.mvp.listener.ItemClickListener;
import com.example.yf.creatorshirt.mvp.model.detaildesign.DetailPatterStyle;
import com.example.yf.creatorshirt.mvp.ui.adapter.base.BaseAdapter;
import com.example.yf.creatorshirt.mvp.ui.adapter.viewholder.design.ItemViewHolder;

/**
 * Created by yangfang on 2017/8/21.
 */

public class PatternStyleAdapter extends BaseAdapter<DetailPatterStyle, ItemViewHolder> {
    private ItemClickListener.OnItemClickListener clickListener;

    public PatternStyleAdapter(Context context) {
        super(context);
    }

    @Override
    protected ItemViewHolder createItemViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder holder = null;
        holder = new ItemViewHolder(parent, R.layout.item_style_layout);
        return holder;
    }

    @Override
    protected void bindCustomViewHolder(final ItemViewHolder holder, final int position) {
        holder.mStyleTextView.setText(mData.get(position).getName());
        holder.mCommonStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(holder.mCommonStyle, position);
            }
        });
    }

    public void setOnClickListener(ItemClickListener.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
}