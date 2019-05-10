package com.kotlin.demo.di.component;

import android.app.Activity;
import com.kotlin.demo.di.module.FragmentModule;
import com.kotlin.demo.di.scope.FragmentScope;
import dagger.Component;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    /*void inject(QuickQuoteFragment quickQuoteFragment);

    void inject(LatestQuoteFragment latestQuoteFragment);

    void inject(HistoryReportFragment historyReportFragment);

    void inject(HistoryQuoteFragment historyQuoteFragment);

    void inject(PersonalCenterFragment personalCenterFragment);*/
}
