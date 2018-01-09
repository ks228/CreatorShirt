package com.example.yf.creatorshirt.mvp.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.example.yf.creatorshirt.R;
import com.example.yf.creatorshirt.mvp.ui.view.sticker.StickerView;
import com.example.yf.creatorshirt.utils.DisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Creatd by yangfang on 2017/8/26.
 */

public class ClothesBackView extends StickerView {
    @BindView(R.id.clothes)
    ClothesImageView mClothes;
    @BindView(R.id.source)
    FinishImage mSource;
    @BindView(R.id.mask)
    MaskView mMask;
    private Bitmap mask;

    public ClothesBackView(Context context) {
        super(context);
        initView(context);
    }

    public ClothesBackView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ClothesBackView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.clothes_back_layout, this);
        ButterKnife.bind(this, view);
        setBackgroundColor(Color.WHITE);
        setLocked(false);
        setConstrained(true);
    }


    public void setChoiceDone() {
        setLocked(true);
    }

    public void removeStickerView() {
        if (getCurrentSticker() != null) {
            removeCurrentSticker();
            setLocked(true);
        }
    }


    public void setColorBg(int resource) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resource);
        if (mask != null) {
            mClothes.setImageSource(bitmap);
            mMask.setImageSource(bitmap);
            mMask.setImageMask(mask);
        } else {
            mClothes.setImageSource(bitmap);
        }
    }


    public void setImageMask(final Bitmap mask, final Bitmap source) {
        DisplayUtil.calculateDesignerClothesWidth(getContext(), mMask);
        mMask.setImageSource(source);
        mMask.setImageMask(mask);
        this.mask = mask;
    }

    public void setImageSource(Bitmap resource) {
        mSource.setImageNetSource(resource);
    }

}
