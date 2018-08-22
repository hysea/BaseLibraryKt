package com.hysea.library.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView

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


/**
 * 显示键盘
 */
fun EditText.showInputMethod() {
    this.requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

/**
 * 隐藏键盘
 */
fun EditText.hideInputMethod() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(this.windowToken, 0)
}

/**
 * 获取文本控件内容
 */
fun TextView.getContent(): String {
    return this.text.toString()
}