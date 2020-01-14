package com.hysea.library.utils

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView

/**
 * View相关工具类
 * Created by hysea on 2018/8/20.
 */
fun <T : Any> T.tag(): String = this::class.java.simpleName


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

/**
 * 判断触点是否落在该View上
 */
fun isTouchInView(ev: MotionEvent, v: View): Boolean {
    val location = IntArray(2)
    v.getLocationOnScreen(location)

    return ev.rawX >= location[0] && ev.rawX <= location[0] + v.width
            && ev.rawY >= location[1] && ev.rawY <= location[1] + v.height
}

/**
 * 显示键盘
 */
fun View.showInputMethod() {
    this.requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

/**
 * 隐藏键盘
 */
fun View.hideInputMethod() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(this.windowToken, 0)
}

fun TextView.getContent() = this.text.toString()

