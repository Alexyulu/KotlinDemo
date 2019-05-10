package com.kotlin.demo.ui.activity

import android.text.TextUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.NetworkUtils
import com.jakewharton.rxbinding3.view.clicks
import com.kotlin.demo.R
import com.kotlin.demo.base.BaseActivity
import com.kotlin.demo.model.contract.LoginContract
import com.kotlin.demo.model.model.LoginBean
import com.kotlin.demo.presenter.LoginPresenter
import com.kotlin.demo.util.Utils
import com.kotlin.demo.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.TimeUnit

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/5/6
 * Desc :
 */
class LoginActivity : BaseActivity<LoginPresenter>(), LoginContract.LoginView {
    override fun onLoginSuccess(loginBean: LoginBean?) {
        LogUtils.d(loginBean.toString())
    }

    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun initEventAndDate() {

        baseCompositeDisposable!!.add(btn_login.clicks()
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .subscribe { onLoginClick() })
    }

    private fun onLoginClick() {
        if (!NetworkUtils.isConnected()) {
            toast("网络不可用")
            return
        }

        val userPhone = Utils.getText(et_user)
        val passCode = Utils.getText(et_user_pass)

        when {
            TextUtils.isEmpty(userPhone) -> {
                toast("账号不能为空")
                Utils.requestFocus(et_user)
            }
            TextUtils.isEmpty(passCode) -> {
                toast("密码不能为空")
                Utils.requestFocus(et_user_pass)
            }
            else -> {
                //登录
                //stateLoading()
                mPresenter.login(userPhone!!, passCode!!)
            }
        }
    }

}