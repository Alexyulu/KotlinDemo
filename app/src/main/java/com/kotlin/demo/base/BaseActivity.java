package com.kotlin.demo.base;

import android.content.Intent;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kotlin.demo.app.App;
import com.kotlin.demo.di.component.ActivityComponent;
import com.kotlin.demo.di.component.DaggerActivityComponent;
import com.kotlin.demo.di.module.ActivityModule;
import com.kotlin.demo.ui.activity.LoginActivity;
import com.kotlin.demo.util.SnackBarUtil;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : MVP activity基类
 */

public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {
    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.Companion.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null)
            mPresenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detach();
        super.onDestroy();
    }

    @Override
    public void showErrorMsg(@NotNull String msg) {
        dismissProgressBar();
        SnackBarUtil.show(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void operateErrorCode(int code, @NotNull String errorMsg) {
        dismissProgressBar();
        ToastUtils.showShort(errorMsg);

        if (code == 206) { //身份验证失效
            //关闭所有页面 重新打开登陆页面
            //MyApp.getInstance().finishAllActivty();
            SPUtils.getInstance().put("isLogin", false);
            Intent intent = new Intent(getMContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void stateError() {
        dismissProgressBar();
    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {
        createProgressBar();
    }

    @Override
    public void stateMain() {
        dismissProgressBar();
    }

    protected abstract void initInject();
}
