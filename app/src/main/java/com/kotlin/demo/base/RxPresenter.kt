package com.kotlin.demo.base

import com.kotlin.demo.component.RxBus
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/5/5
 * Desc :
 */
open class RxPresenter<T : BaseView> : BasePresenter<T> {

    protected var mView : T? = null
    private var mCompositeDisposable : CompositeDisposable? = null

    override fun attach(view: T) {
        this.mView = view
    }

    override fun detach() {
        this.mView = null
        unSubscribe()
    }

    private fun unSubscribe() {
        if (mCompositeDisposable != null) mCompositeDisposable!!.clear()
    }

    protected fun <U> addRxBusSubscribe(eventType: Class<U>, act: Consumer<U>) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable!!.add(RxBus.default.toDefaultFlowable(eventType, act))
    }

    protected fun addSubscribe(subscription: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }

        mCompositeDisposable!!.add(subscription)
    }


}