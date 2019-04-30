package com.kotlin.demo.util

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.kotlin.demo.R

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/4/30
 * Desc :
 */
class ProgressDialogUtils private constructor(context: Context?) {
    private var dialog: MaterialDialog? = null

    init {
        this.dialog = MaterialDialog(context!!)
            .customView(R.layout.layout_progress_dialog)
            .cancelable(false)
    }

    companion object {
        @Synchronized fun getInstance(context: Context?) : ProgressDialogUtils {
            return ProgressDialogUtils(context)
        }
    }

    fun show(): ProgressDialogUtils {
        dialog!!.show()
        return this
    }

    fun dismiss() : ProgressDialogUtils {
        dialog!!.dismiss()
        return this
    }
}