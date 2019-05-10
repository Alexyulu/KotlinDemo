package com.kotlin.demo.model.http.exception

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc : 自定义exception
 */
data class ApiException(var errMsg: String, var code: Int) : Exception()
