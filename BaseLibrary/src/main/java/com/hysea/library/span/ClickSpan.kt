package com.hysea.library.span

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import androidx.annotation.ColorInt

/**
 * create by hysea on 2020/1/17
 */
class ClickSpan(@ColorInt val textColor: Int, val click: (() -> Unit)? = null) : ClickableSpan() {
    override fun onClick(widget: View) {
        click?.invoke()
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        //设置文本的颜色
        ds.color = textColor
        //超链接形式的下划线，false表示不显示下划线，true表示显示下划线
        ds.isUnderlineText = false
    }
}