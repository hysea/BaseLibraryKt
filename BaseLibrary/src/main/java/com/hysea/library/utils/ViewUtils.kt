package com.hysea.library.utils

import android.view.View

/**
 * View相关工具类
 * Created by hysea on 2018/8/20.
 */

/**
 * 判断View控件是否可见
 */
inline val View.isVisible: Boolean
    get() = visibility == View.VISIBLE

/**
 * 隐藏View控件
 */
fun View.hide(gone: Boolean = true) {
    visibility = if (gone) View.GONE else View.INVISIBLE
}

/**
 * 显示View控件
 */
fun View.show() {
    visibility = View.VISIBLE
}

fun <T : Any> T.TAG() = this::class.java.simpleName
