package com.kotlin.demo.model.contract

import com.kotlin.demo.base.BasePresenter
import com.kotlin.demo.base.BaseView
import com.kotlin.demo.model.model.LoginBean

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/5/6
 * Desc :
 */
interface LoginContract {
    interface LoginView : BaseView {
        fun onLoginSuccess(loginBean: LoginBean?)

    }

    interface Presenter : BasePresenter<LoginView> {
        fun login(userPhone: String, passCode: String)
    }
}