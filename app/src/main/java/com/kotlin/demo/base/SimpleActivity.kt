package com.kotlin.demo.base

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import com.kotlin.demo.app.App
import com.kotlin.demo.util.ProgressDialogUtils
import io.reactivex.disposables.CompositeDisposable
import me.yokeyword.fragmentation.SupportActivity

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/4/25
 * Desc :
 */
abstract class SimpleActivity : SupportActivity() {
    protected var mContext: Activity? = null
    var baseCompositeDisposable: CompositeDisposable? = null
    private var dialog: ProgressDialogUtils? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBarColor()
        setContentView(getLayout())
        mContext = this
        onViewCreated()
        App.instance?.addActivity(this)
        if (baseCompositeDisposable == null) baseCompositeDisposable = CompositeDisposable()

        dialog = ProgressDialogUtils.getInstance(this)
        //设置状态栏字体颜色  只有黑色和白色两种  这里为黑色
        statusTextColor()
        initEventAndDate()

    }

    override fun onDestroy() {
        super.onDestroy()
        App.instance?.removeActivity(this)
        if (baseCompositeDisposable != null) baseCompositeDisposable!!.clear()
    }

    fun createProgressBar() {
        dialog?.show()
    }

    fun dismissProgressBar() {
        dialog?.dismiss()
    }

    protected abstract fun getLayout(): Int

    protected abstract fun initEventAndDate()

    protected open fun onViewCreated() {

    }

    private fun statusTextColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    @SuppressLint("PrivateApi")
    private fun statusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                val decorViewClazz = Class.forName("com.android.internal.policy.DecorView")
                val field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor")
                field.isAccessible = true
                field.setInt(window.decorView, Color.TRANSPARENT)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}