package com.kotlin.demo.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/4/25
 * Desc :
 */
class App : Application() {

    /**
     * MuTableSet 与MutableList相似
     * 定义：MuTableSet<类型>或mutableSetOf(元素1，元素2，....,元素n)
     */
    private var allActivities : MutableSet<Activity>? = null

    private var screenWidth : Int = -1
    private var screenHeight : Int = -1
    private var dimenRate : Double = -1.0
    private var dimenDpi : Int = -1


    //静态方法的调用
    companion object{

        private var instance:App? = null

        @Synchronized fun getInstance() : App? = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        //获取品目宽高
        getScreenSize()
    }

    fun addActivity(activity: Activity) {
        if (allActivities == null) allActivities = HashSet()
        allActivities!!.add(activity)
    }

    fun removeActivity(activity: Activity) {
        if (allActivities != null) allActivities!!.remove(activity)
    }

    private fun getScreenSize() {
        val windowManager:WindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()

        val defaultDisplay:Display  = windowManager.defaultDisplay

        defaultDisplay.getMetrics(displayMetrics)

        dimenRate = displayMetrics.density / 1.0
        dimenDpi = displayMetrics.densityDpi
        screenWidth = displayMetrics.widthPixels
        screenHeight = displayMetrics.heightPixels
        if (screenWidth > screenHeight) {
            val temp = screenHeight
            screenHeight = screenWidth
            screenWidth = temp
        }

    }


}