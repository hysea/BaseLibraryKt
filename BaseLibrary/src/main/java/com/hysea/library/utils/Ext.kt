package com.hysea.library.utils

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/**
 * 常用扩展函数
 * Created by hysea on 2018/8/21.
 */

/**
 * Context的扩展函数，获取布局View
 */
fun Context.inflateLayout(@LayoutRes resource: Int, root: ViewGroup? = null, attachToRoot: Boolean = false): View =
        LayoutInflater.from(this).inflate(resource, root, attachToRoot)

/**
 * Context的扩展函数，Toast提示，防止Toast多次弹出
 */
@JvmField
var mToast: Toast? = null

fun Context.showToast(message: String?, length: Int = Toast.LENGTH_SHORT) {
    mToast?.apply {
        setText(message)
        show()
    } ?: run {
        Toast.makeText(this.applicationContext, message, length).apply {
            mToast = this
        }.show()
    }
}

fun Context.showToast(@StringRes strResId: Int) {
    showToast(getString(strResId))
}

