package com.kotlin.demo.model.http.response

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/19
 * Desc :
 */

data class BaseResponse<T>(
    val code: Int,
    val message: String,
    val data: T
) {
    fun isSuccess() : Boolean {
        return code == 200
    }
}
