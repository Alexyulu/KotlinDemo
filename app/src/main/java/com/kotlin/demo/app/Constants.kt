package com.kotlin.demo.app

import java.io.File

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/5/5
 * Desc :
 */
object Constants {
    val pathData : String = App.instance!!.cacheDir.absolutePath + File.separator + "data"

    /**
     * 当前用户是否是首次登陆的key
     */
    val spIsFirstOpen = "isFirstOpen"
}