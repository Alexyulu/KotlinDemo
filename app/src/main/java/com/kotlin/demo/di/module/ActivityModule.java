package com.kotlin.demo.di.module;

import android.app.Activity;
import com.kotlin.demo.di.scope.ActivityScope;
import dagger.Module;
import dagger.Provides;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */
@Module
public class ActivityModule {
    public Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
