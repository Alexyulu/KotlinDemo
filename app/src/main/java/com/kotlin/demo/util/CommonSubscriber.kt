package com.kotlin.demo.util

import com.blankj.utilcode.util.LogUtils
import com.google.gson.JsonParseException
import com.kotlin.demo.base.BaseView
import com.kotlin.demo.model.http.exception.ApiException
import io.reactivex.subscribers.ResourceSubscriber
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.text.ParseException

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/5/8
 * Desc :
 */
abstract class CommonSubscriber<T>(view: BaseView) : ResourceSubscriber<T>() {
    private var mView: BaseView? = view

    override fun onComplete() {
        mView?.stateMain()
    }

    override fun onError(t: Throwable) {
        if (mView == null) return
        mView!!.stateError()
        if (t is ApiException) {
            mView!!.operateErrorCode(t.code, t.errMsg)
        } else if (t is HttpException) {
            mView!!.showErrorMsg("数据加载失败")
        } else if (t is ConnectException) {
            mView!!.showErrorMsg("连接服务器失败")
        } else if (t is SocketTimeoutException) {
            mView!!.showErrorMsg("连接服务器超时")
        } else if (t is NumberFormatException) {
            mView!!.showErrorMsg("数据转换异常")
            t.printStackTrace()
        } else if (t is JsonParseException
                || t is JSONException
                || t is ParseException) {
            mView!!.showErrorMsg("数据解析错误")
        } else if (t is NullPointerException) {
            mView!!.showErrorMsg("无数据")
            t.printStackTrace()
        } else {
            mView!!.showErrorMsg("未知错误")
            t.printStackTrace()
        }
        LogUtils.e(t)
    }
}