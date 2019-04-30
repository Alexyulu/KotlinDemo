package com.kotlin.demo.base

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/4/30
 * Desc :
 */
open interface BaseView {
    fun showErrorMsg(msg: String)

    //===state===

    fun stateError()

    fun stateEmpty()

    fun stateLoading()

    fun stateMain()

    fun operateErrorCode(code: Int, errorMsg: String)
}