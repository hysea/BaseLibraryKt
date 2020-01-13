package com.hysea.library.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

/**
 * Context的扩展
 * create by hysea on 2020/1/13
 */

fun Context.showToast(text: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun Context.showToast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    showToast(getString(id), duration)
}

fun Context.inflateLayout(@LayoutRes resource: Int, root: ViewGroup? = null, attachToRoot: Boolean = false): View {
       return LayoutInflater.from(this).inflate(resource, root, attachToRoot)
}

fun Context.getResColor(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this, id)
}

fun Context.getResDrawable(@DrawableRes id: Int): Drawable? {
    return ContextCompat.getDrawable(this, id)
}
