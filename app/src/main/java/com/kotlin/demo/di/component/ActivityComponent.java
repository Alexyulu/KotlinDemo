package com.kotlin.demo.di.component;

import android.app.Activity;

import com.kotlin.demo.di.module.ActivityModule;
import com.kotlin.demo.di.scope.ActivityScope;
import com.kotlin.demo.ui.activity.LoginActivity;

import dagger.Component;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(LoginActivity activity);

    /*void inject(LoginActivity activity);

    void inject(MainActivity mainActivity);

    void inject(CarReportActivity carReportActivity);

    void inject(AccountManagerActivity accountManagerActivity);

    void inject(CreateAccountActivity createAccountActivity);

    void inject(BusinessManagerActivity businessManagerActivity);

    void inject(CreateBusinessActivity createBusinessActivity);

    void inject(BasicSettingActivity basicSettingActivity);

    void inject(FeedbackSettingActivity feedbackSettingActivity);

    void inject(AuditPolicyActivity auditPolicyActivity);

    void inject(QuoteSettingActivity quoteSettingActivity);

    void inject(SelectCityActivity selectCityActivity);

    void inject(SelectCarModelActivity selectCarModelActivity);

    void inject(CarConfigActivity carConfigActivity);

    void inject(ChangePasswordActivity changePasswordActivity);*/
}
