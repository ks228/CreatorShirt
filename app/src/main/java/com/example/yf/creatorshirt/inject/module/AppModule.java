package com.example.yf.creatorshirt.inject.module;

import com.example.yf.creatorshirt.app.App;
import com.example.yf.creatorshirt.mvp.model.db.DataManager;
import com.example.yf.creatorshirt.mvp.model.db.HttpHelper;
import com.example.yf.creatorshirt.mvp.model.db.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yang on 16/05/2017.
 * 依赖注入所需要的实体类Application
 */
@Module
public class AppModule {
    private final App mApplication;

    public AppModule(App application) {
        mApplication = application;
    }

    /**
     * 提供Application的依赖
     *
     * @return
     */
    @Provides
    @Singleton
    App providerApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DataManager providerDataManager(HttpHelper helper){
        return new DataManager(helper);
    }
}