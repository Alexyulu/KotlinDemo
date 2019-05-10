package com.kotlin.demo.di.component;


import com.kotlin.demo.app.App;
import com.kotlin.demo.di.module.AppModule;
import com.kotlin.demo.di.module.HttpModule;
import com.kotlin.demo.model.DataManager;
import com.kotlin.demo.model.http.RetrofitHelper;
import com.kotlin.demo.model.prefs.ImplPreferencesHelper;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    ImplPreferencesHelper preferencesHelper(); //提供sp帮助类
}
