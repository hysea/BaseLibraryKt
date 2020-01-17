package com.hysea.library.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.*
import androidx.core.content.ContextCompat

/**
 * Context的扩展
 * create by hysea on 2020/1/13
 */

inline val Context.screenWidth
    get() = resources.displayMetrics.widthPixels

inline val Context.screenHeight
    get() = resources.displayMetrics.heightPixels

inline val Context.versionName
    get() = packageManager.getPackageInfo(packageName, 0).versionName

inline val Context.versionCode
    get() = with(packageManager.getPackageInfo(packageName, 0)) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            longVersionCode
        } else {
            versionCode.toLong()
        }
    }

fun Context.dp2px(value: Float): Int = (value * resources.displayMetrics.density).toInt()
fun Context.px2dp(value: Int): Float = value / resources.displayMetrics.density
fun Context.sp2px(value: Float): Int = (value * resources.displayMetrics.scaledDensity).toInt()
fun Context.px2sp(value: Int): Float = value / resources.displayMetrics.scaledDensity

fun Context.dimen2px(@DimenRes resId: Int): Int = resources.getDimensionPixelSize(resId)
fun Context.string(@StringRes resId: Int): String = getString(resId)
fun Context.color(@ColorRes resId: Int): Int = ContextCompat.getColor(this, resId)
fun Context.drawable(@DrawableRes resId: Int): Drawable? = ContextCompat.getDrawable(this, resId)

fun Context.showToast(text: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun Context.showToast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    showToast(string(id), duration)
}

fun Context.inflateLayout(@LayoutRes resource: Int, root: ViewGroup? = null, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this).inflate(resource, root, attachToRoot)
}

fun <T : Any> Context.readGo(clazz: Class<T>, bundle: Bundle? = null, isFinish: Boolean = false) {
    Intent(this, clazz).also { intent ->
        bundle?.let { intent.putExtras(it) }
        startActivity(intent)
        if (this is Activity && isFinish) {
            this.finish()
        }
    }
}

fun <T : Any> Context.readGoForResult(clazz: Class<T>, requestCode: Int, bundle: Bundle? = null, isFinish: Boolean = false) {
    Intent(this, clazz).also { intent ->
        bundle?.let { intent.putExtras(it) }
        if (this is Activity) {
            this.startActivityForResult(intent, requestCode)
            if (isFinish) {
                this.finish()
            }
        }
    }
}