package com.kotlin.demo.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kotlin.demo.app.App;
import com.kotlin.demo.di.component.DaggerFragmentComponent;
import com.kotlin.demo.di.component.FragmentComponent;
import com.kotlin.demo.di.module.FragmentModule;
import com.kotlin.demo.ui.activity.LoginActivity;
import com.kotlin.demo.util.SnackBarUtil;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : BaseFragment
 */

public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {
    @Inject
    protected T mPresenter;

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.Companion.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attach(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.detach();
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(@NotNull String msg) {
        dismissProgressBar();
        FragmentActivity activity = getActivity();
        try {
            if (activity != null)
                SnackBarUtil.show(((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0), msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
