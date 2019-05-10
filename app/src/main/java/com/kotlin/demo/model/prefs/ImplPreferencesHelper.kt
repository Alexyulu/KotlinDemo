package com.kotlin.demo.model.prefs

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import androidx.core.content.edit
import com.google.gson.Gson
import com.kotlin.demo.app.App
import com.kotlin.demo.model.model.LoginBean
import javax.inject.Inject

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/5/5
 * Desc :
 */
class ImplPreferencesHelper @Inject constructor() : PreferencesHelper {

    private var mSPrefs : SharedPreferences? = null

    companion object {
        private const val SP_NAME = "mySP"

        private const val KEY_ACCOUNT = "account"
    }

    init {
        mSPrefs = App.instance!!.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
    }

    fun getLoginInfo() : LoginBean? {
        val accountJson = mSPrefs!!.getString(KEY_ACCOUNT, null)
        return if (!TextUtils.isEmpty(accountJson)) {
            Gson().fromjson(accountJson)
        } else {
            null
        }
    }

    fun setLoginInfo(loginBean: LoginBean) {
        mSPrefs!!.edit {
            putString(KEY_ACCOUNT, Gson().toJson(loginBean))
        }
    }

    //gson解析简化类
    private inline fun <reified T> Gson.fromjson(json: String?) = fromJson(json, T::class.java)
}