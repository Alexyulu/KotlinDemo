package com.kotlin.demo.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.kotlin.demo.R
import com.kotlin.demo.util.ProgressDialogUtils
import io.reactivex.disposables.CompositeDisposable
import me.yokeyword.fragmentation.SupportFragment

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/5/5
 * Desc :
 */
abstract class SimpleFragment : SupportFragment() {
    protected var mView: View? = null
    protected var mActivity: Activity? = null
    protected var mContext: Context? = null
    protected var isInit: Boolean = false

    var baseCompositeDisposable: CompositeDisposable? = null
    private var progressInstance: ProgressDialogUtils? = null


    override fun onAttach(context: Context) {
        mContext = context
        super.onAttach(context)
        mActivity = context as Activity?
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(getLayoutId(), null)
        progressInstance = ProgressDialogUtils.getInstance(mContext)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (baseCompositeDisposable == null) baseCompositeDisposable = CompositeDisposable()
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        isInit = true
        initEventAndData()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (baseCompositeDisposable != null) baseCompositeDisposable!!.clear()
    }

    fun createProgressBar() {
        progressInstance?.show()
    }

    fun dismissProgressBar() {
        progressInstance?.dismiss()
    }

    fun initToolBar(title: String, backUpButtonEnabled: Boolean) {
        var view = mView!!.findViewById<View>(R.id.toolbar)
        var toolbarTitle = mView!!.findViewById<TextView>(R.id.toolbar_title_tv)
        var btnBack = mView!!.findViewById<TextView>(R.id.btn_back)

        if (view != null) {
            var toolbar = view as Toolbar
            toolbar.title = ""
            toolbarTitle.text = title
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
            btnBack.visibility = if (backUpButtonEnabled) View.VISIBLE else View.GONE
            btnBack.setOnClickListener { onBackPressedSupport() }
        }
    }

    abstract fun initEventAndData()

    abstract fun getLayoutId(): Int
}