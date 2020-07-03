package com.hysea.library.utils

import android.content.Context
import android.graphics.Paint
import android.graphics.RectF
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

fun View.onGlobalLayout(callback: () -> Unit) = with(viewTreeObserver) {
    addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            removeOnGlobalLayoutListener(this)
            callback()
        }
    })
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

/**
 * 获取EditText内容
 */
fun EditText.getContent() = this.text.toString()

val RecyclerView.gridLayoutManager: GridLayoutManager?
    get() = layoutManager as? GridLayoutManager

val RecyclerView.linearLayoutManager: LinearLayoutManager?
    get() = layoutManager as? LinearLayoutManager

/**
 * 计算Text基线的y，用于draw时文字居中
 *
 * y = 矩形中心y值 + 矩形中心与基线的距离
 *
 * 距离 = 文字高度的一半 - 基线到文字底部的距离（也就是bottom）
 */
fun calcTextSuitBaseY(rectF: RectF, fontMetrics: Paint.FontMetrics): Float {
    return rectF.height() / 2 + ((fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom)
}





