package com.example.yf.creatorshirt.inject.component;

import android.app.Activity;

import com.example.yf.creatorshirt.inject.module.ActivityModule;
import com.example.yf.creatorshirt.inject.scope.AcitivityScope;
import com.example.yf.creatorshirt.mvp.ui.activity.AddressActivity;
import com.example.yf.creatorshirt.mvp.ui.activity.DesignActivity;
import com.example.yf.creatorshirt.mvp.ui.activity.DetailDesignActivity;
import com.example.yf.creatorshirt.mvp.ui.activity.LoginActivity;

import dagger.Component;

/**
 * Created by yang on 16/05/2017.
 */

@AcitivityScope
@Component(dependencies = {AppComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity getActivity();

//    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(AddressActivity addressActivity);

    void inject(DesignActivity designActivity);

    void inject(DetailDesignActivity detailDesignActivity);
//    void inject(ChoiceSizeActivity choiceSizeActivity);
}
