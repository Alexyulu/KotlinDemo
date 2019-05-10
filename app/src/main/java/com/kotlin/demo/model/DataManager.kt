package com.kotlin.demo.model

import com.kotlin.demo.model.http.HttpHelper
import com.kotlin.demo.model.http.response.BaseResponse
import com.kotlin.demo.model.model.LoginBean
import com.kotlin.demo.model.prefs.PreferencesHelper
import io.reactivex.Flowable
import java.util.*

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/5/5
 * Desc :
 */
class DataManager(var httpHelper: HttpHelper?, var preferencesHelper: PreferencesHelper?) : HttpHelper, PreferencesHelper {
    override fun fetchLogin(map: HashMap<String, Any>): Flowable<BaseResponse<LoginBean>> {
        return httpHelper!!.fetchLogin(map)
    }

}