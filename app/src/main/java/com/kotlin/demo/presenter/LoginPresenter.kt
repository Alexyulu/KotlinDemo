package com.kotlin.demo.presenter

import com.kotlin.demo.base.RxPresenter
import com.kotlin.demo.model.DataManager
import com.kotlin.demo.model.contract.LoginContract
import com.kotlin.demo.model.model.LoginBean
import com.kotlin.demo.util.CommonSubscriber
import com.kotlin.demo.util.RxUtil
import javax.inject.Inject

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/5/6
 * Desc :
 */
class LoginPresenter @Inject constructor(dataManager: DataManager) : RxPresenter<LoginContract.LoginView>(), LoginContract.Presenter {
    override fun login(userPhone: String, passCode: String) {

        val map = hashMapOf<String, Any>()
        map["userPhone"] = userPhone
        map["password"] = passCode

        addSubscribe(dataManager!!.fetchLogin(map)
                .compose(RxUtil.done())
                .doOnSubscribe { mView!!.stateLoading() }
                .subscribeWith(object : CommonSubscriber<LoginBean>(mView!!){
                    override fun onNext(t: LoginBean?) {
                        mView!!.onLoginSuccess(t)
                    }
                }))


    }

    private var dataManager : DataManager? = dataManager

}