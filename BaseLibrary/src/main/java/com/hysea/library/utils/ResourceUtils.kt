package com.hysea.library.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.hysea.library.base.BaseApp

/**
 * 资源相关工具类
 * Created by hysea on 2018/8/20.
 */


fun getString(@StringRes resId: Int): String {
    return BaseApp.instance.getString(resId)
}

/**
 * 获取Drawable，兼容低版本
 */
fun getDrawable(@DrawableRes resId: Int): Drawable? {
    return ContextCompat.getDrawable(BaseApp.instance, resId)
}

/**
 * 获取颜色，兼容低版本
 */
@ColorInt
fun getColor(context: Context, @ColorRes resId: Int): Int {
    return ContextCompat.getColor(context, resId)
}
