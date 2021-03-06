package com.example.yf.creatorshirt.utils.systembar;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yang on 07/06/2017.
 */

public class BarParams implements Cloneable{
    @ColorInt
    public int statusBarColor = Color.TRANSPARENT; //状态栏颜色
    @ColorInt
    public int navigationBarColor = Color.BLACK;  //导航栏颜色
    @FloatRange(from = 0f, to = 1f)
    public float statusBarAlpha = 0.0f;           //状态栏透明度
    @FloatRange(from = 0f, to = 1f)
    public float navigationBarAlpha = 0.0f;       //导航栏透明度
    public boolean fullScreen = false;            //有导航栏的情况，全屏显示
    public boolean fullScreenTemp = fullScreen;
    public BarHide barHide = BarHide.FLAG_SHOW_BAR;  //隐藏Bar
    public boolean darkFont = false;                 //状态栏字、体深色与亮色标志位
    public boolean statusBarFlag = false;            //是否可以修改状态栏颜色
    @ColorInt
    public int statusBarColorTransform = Color.BLACK;  //状态栏变换后的颜色
    @ColorInt
    public int navigationBarColorTransform = Color.BLACK;  //导航栏变换后的颜色
    public View view;                                      //支持view变色
    public Map<View, Map<Integer, Integer>> viewMap = new HashMap<>();     //支持view变色
    @ColorInt
    public int viewColorBeforeTransform = statusBarColor;     //view变色前的颜色
    @ColorInt
    public int viewColorAfterTransform = statusBarColorTransform;  //view变色后的颜色
    @FloatRange(from = 0f, to = 1f)
    public float viewAlpha = 0.0f;
    public boolean fits = false;                                   //解决标题栏与状态栏重叠问题
    public int navigationBarColorTem = navigationBarColor;
    public View statusBarView;                       //4.4自定义一个状态栏
    public View navigationBarView;                //4.4自定义一个导航栏
    public View statusBarViewByHeight;            //解决标题栏与状态栏重叠问题
    @ColorInt
    public int flymeOSStatusBarFontColor;          //flymeOS状态栏字体变色
    public boolean isSupportActionBar = false;    //结合actionBar使用
    public View titleBarView;                     //标题栏view
    public int titleBarHeight;                    //标题栏的高度

    @Override
    protected BarParams clone() {
        BarParams barParams = null;
        try {
            barParams = (BarParams) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return barParams;
    }
}
