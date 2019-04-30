package com.kotlin.demo.base

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/4/30
 * Desc :
 */
interface BasePresenter<T : BaseView> {
    fun attach(view: T)

    fun detach()
}