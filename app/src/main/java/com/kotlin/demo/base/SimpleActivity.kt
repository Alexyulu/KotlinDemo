package com.kotlin.demo.base

import android.app.Activity
import android.os.Bundle
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
        setContentView(getLayout())
        mContext = this
        onViewCreated()
        App.getInstance()?.addActivity(this)
        if (baseCompositeDisposable == null) baseCompositeDisposable = CompositeDisposable()

        dialog = ProgressDialogUtils.getInstance(this)

        initEventAndDate()

    }

    override fun onDestroy() {
        super.onDestroy()
        App.getInstance()?.removeActivity(this)
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

    protected fun onViewCreated() {

    }
}