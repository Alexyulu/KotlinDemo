package com.kotlin.demo.model.http.api

import com.kotlin.demo.app.ApiUrl
import com.kotlin.demo.model.http.response.BaseResponse
import com.kotlin.demo.model.model.LoginBean
import io.reactivex.Flowable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/5/5
 * Desc :
 */
interface MainApi {

    @FormUrlEncoded
    @POST(ApiUrl.APP_LOGIN)
    fun login(@FieldMap hashMap: HashMap<String, Any>) : Flowable<BaseResponse<LoginBean>>
}