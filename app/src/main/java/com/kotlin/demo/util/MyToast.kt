package com.kotlin.demo.util

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.ToastUtils

fun Activity.toast(msg: String) {
    toToast(this, msg)
}

fun Fragment.toast(msg: String) {
    if (activity == null) {
        return
    }
    toToast(activity as Context, msg)
}

fun Context.toast(msg: String) {
    toToast(this, msg)
}

fun Activity.toast(@StringRes msgId: Int) {
    if (msgId == 0) {
        return
    }
    val msg = getString(msgId)
    toToast(this, msg)
}

fun Fragment.toast(@StringRes msgId: Int) {
    if (activity == null) {
        return
    }
    if (msgId == 0) {
        return
    }
    val msg = getString(msgId)
    toToast(activity as Context, msg)
}

fun Context.toast(@StringRes msgId: Int) {
    if (msgId == 0) {
        return
    }
    val msg = getString(msgId)
    toToast(this, msg)
}

private fun toToast(activity: Context, msg: String) {
    if (TextUtils.isEmpty(msg)) {
        return
    }
    ToastUtils.showShort(msg)
}