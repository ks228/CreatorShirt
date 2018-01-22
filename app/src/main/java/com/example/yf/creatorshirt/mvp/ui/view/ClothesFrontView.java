package com.example.yf.creatorshirt.mvp.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.yf.creatorshirt.R;
import com.example.yf.creatorshirt.app.App;
import com.example.yf.creatorshirt.mvp.model.detaildesign.TextEntity;
import com.example.yf.creatorshirt.mvp.ui.view.sticker.SignatureDialog;
import com.example.yf.creatorshirt.mvp.ui.view.sticker.Sticker;
import com.example.yf.creatorshirt.mvp.ui.view.sticker.StickerView;
import com.example.yf.creatorshirt.mvp.ui.view.sticker.TextSticker;
import com.example.yf.creatorshirt.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangfang on 2018/1/3.
 */

public class ClothesFrontView extends StickerView {
    @BindView(R.id.clothes)
    ClothesView mClothes;
    @BindView(R.id.source)
    PatterImage mSource;
    @BindView(R.id.mask)
    ImageView mMask;
    private TextSticker textSticker;
    private Typeface mUpdateType;
    private List<TextEntity> textEntities = new ArrayList<>();//保存字体
    private String mImagecolor;
    private Context mContext;

    public ClothesFrontView(Context context) {
        super(context);
        initView(context);
    }

    public ClothesFrontView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.clothes_back_layout, this);
        ButterKnife.bind(this, view);
        setBackgroundColor(Color.WHITE);
        setLocked(false);
        setConstrained(true);
        initSignature();

    }

    /**
     * 背景衣服变化
     *
     * @param resource
     */
    public void setColorBg(int resource) {
        mClothes.setImageSource(Utils.getBitmapResource(resource));

    }

    /**
     * 形成遮罩图片
     *
     * @param maskBitmap
     */
    public void setImageMask(final Bitmap maskBitmap) {
        mMask.setImageBitmap(maskBitmap);
    }

    /**
     * 放大缩小自定义图片
     *
     * @param resource
     */
    public void setImageSource(Bitmap resource) {
        mSource.setImageNetSource(resource);
    }

    public void initSignature() {
        textSticker = new TextSticker(App.getInstance());
        setBackgroundColor(Color.WHITE);
        setLocked(false);
        setConstrained(true);
        setOnStickerOperationListener(new StickerView.OnStickerOperationListener() {
            @Override
            public void onStickerAdded(@NonNull Sticker sticker) {
            }

            @Override
            public void onStickerClicked(@NonNull Sticker sticker) {
                if (sticker instanceof TextSticker) {
                    mUpdateType = ((TextSticker) sticker).getTypeface();
                    setSignatureText(((TextSticker) sticker).getText(), true);
                }
            }

            @Override
            public void onStickerDeleted(@NonNull Sticker sticker) {
            }

            @Override
            public void onStickerDragFinished(@NonNull Sticker sticker) {
            }

            @Override
            public void onStickerZoomFinished(@NonNull Sticker sticker) {
            }

            @Override
            public void onStickerFlipped(@NonNull Sticker sticker) {
            }

            @Override
            public void onStickerDoubleTapped(@NonNull Sticker sticker) {
            }
        });
    }


    /**
     * 设置自定义字体
     *
     * @param message
     * @param isNew
     */
    public void setSignatureText(String message, final boolean isNew) {
        final SignatureDialog dialog = new SignatureDialog(App.getInstance(), mUpdateType);
        dialog.show();
        if (message != null) {
            dialog.setMessage(message);
        }
        Window win = dialog.getWindow();
        if (win == null) {
            return;
        }
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        win.setAttributes(lp);
        dialog.setCompleteCallBack(new SignatureDialog.CompleteCallBack() {
            @Override
            public void onClickChoiceOrBack(View view, String s, Typeface typeface) {
                if (isNew) {
                    if (textSticker != null) {
                        textSticker.setText(s);
                        textSticker.resizeText();
                        textSticker.setTypeface(typeface);
                        replace(textSticker);
                        invalidate();
                    }

                } else {
                    textSticker = new TextSticker(getContext());
                    textSticker.setText(s);
                    textSticker.setTypeface(typeface);
                    textSticker.setTextColor(Color.BLACK);
                    textSticker.setTextAlign(Layout.Alignment.ALIGN_CENTER);
                    textSticker.resizeText();
                    addSticker(textSticker);
                }
            }
        });
    }

    public void saveText(TextSticker textSticker) {
        if (!TextUtils.isEmpty(textSticker.getText()) && textEntities != null) {
            for (TextEntity t : textEntities) {
                if (t.getText().equals(textSticker.getText())) {
                    textEntities.remove(t);
                }
            }
        }
        TextEntity textEntity;
        if (!TextUtils.isEmpty(textSticker.getText())) {
            textEntity = new TextEntity();
            textEntity.setText(textSticker.getText());
            textEntity.setColor(mImagecolor);
            textEntities.add(textEntity);
        }
    }

    public List<TextEntity> getTextEntities() {
        return textEntities;
    }

    public void setTextColor(int color, String textcolor) {
        textSticker.setTextColor(color);
        this.mImagecolor = textcolor;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }
}