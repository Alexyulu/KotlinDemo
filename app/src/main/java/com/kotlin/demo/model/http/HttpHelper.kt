package com.kotlin.demo.model.http

import com.kotlin.demo.model.http.response.BaseResponse
import com.kotlin.demo.model.model.LoginBean
import io.reactivex.Flowable
import java.util.*

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/5/5
 * Desc :
 */
interface HttpHelper {
    fun fetchLogin(map : HashMap<String, Any>) : Flowable<BaseResponse<LoginBean>>
}