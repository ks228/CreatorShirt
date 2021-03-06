package com.example.yf.creatorshirt.mvp.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.yf.creatorshirt.R;
import com.example.yf.creatorshirt.mvp.model.BombStyleBean;
import com.example.yf.creatorshirt.mvp.ui.adapter.base.BaseAdapter;
import com.example.yf.creatorshirt.mvp.ui.adapter.viewholder.StyleViewHolder;

/**
 * Created by yang on 31/07/2017.
 */

public class BombStyleAdapter extends BaseAdapter<BombStyleBean, StyleViewHolder> {
    private Context mContext;

    public BombStyleAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected StyleViewHolder createItemViewHolder(ViewGroup parent, int viewType) {
        return new StyleViewHolder(parent, R.layout.item_square_style);
    }

    @Override
    protected void bindCustomViewHolder(StyleViewHolder holder, final int position) {
        if(!TextUtils.isEmpty(mData.get(position).getFinishimage())) {
            holder.mClothePrice.setText("¥" + String.valueOf(mData.get(position).getFee()));
            holder.mCreatorName.setText(mData.get(position).getBaseName());
            Glide.with(mContext).load(mData.get(position).getFinishimage()).into(holder.mImageView);
            holder.mClothePriseNum.setText(String.valueOf(mData.get(position).getPraise()) + "人赞");
            holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(clickListener != null)
                    clickListener.onItemClick(v, position,null);
                }
            });
        }

    }

}
