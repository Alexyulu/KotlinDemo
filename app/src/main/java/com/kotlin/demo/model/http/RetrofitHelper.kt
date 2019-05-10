package com.kotlin.demo.model.http

import com.kotlin.demo.model.http.api.MainApi
import com.kotlin.demo.model.http.response.BaseResponse
import com.kotlin.demo.model.model.LoginBean
import io.reactivex.Flowable
import java.util.*
import javax.inject.Inject

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/5/5
 * Desc :
 */
class RetrofitHelper @Inject constructor(mainApi: MainApi) : HttpHelper {
    override fun fetchLogin(map: HashMap<String, Any>): Flowable<BaseResponse<LoginBean>> {
        return mMainApi!!.login(map)
    }

    private var mMainApi: MainApi? = mainApi


}