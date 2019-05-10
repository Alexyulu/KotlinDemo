package com.kotlin.demo.model.http.response

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/10/20
 * Desc : 没有body时的接收类
 */

data class ResponseEmptyBody(
    val code: Int,
    val message: String
) {
    override fun toString(): String {
        return "ResponseEmptyBody(code=$code, message='$message')"
    }
}
