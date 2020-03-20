package com.hysea.library.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.util.DisplayMetrics
import android.view.WindowManager
import com.hysea.library.base.appContext


/**
 * 屏幕相关工具类
 * Created by hysea on 2018/8/20.
 */

/**
 * 获取屏幕宽度
 */
fun getScreenWidth(context: Context): Int {
    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val outMetrics = DisplayMetrics()
    wm.defaultDisplay.getMetrics(outMetrics)
    return outMetrics.widthPixels
}

/**
 * 获取屏幕高度
 */
fun getScreenHeight(context: Context): Int {
    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val outMetrics = DisplayMetrics()
    wm.defaultDisplay.getMetrics(outMetrics)
    return outMetrics.heightPixels
}

/**
 * set fullScreen
 */
fun setFullScreen(activity: Activity?) {
    activity?.window?.addFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN or
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
}

/**
 * set nonFullScreen
 */
fun setNonFullScreen(activity: Activity?) {
    activity?.window?.clearFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN
                or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
}


/**
 * Toggle full screen.
 */
fun toggleFullScreen(activity: Activity?) {
    val fullScreenFlag = WindowManager.LayoutParams.FLAG_FULLSCREEN
    activity?.window?.let {
        if ((it.attributes.flags and fullScreenFlag) == fullScreenFlag) {
            it.clearFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN or
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        } else {
            it.addFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN or
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }
}

/**
 * 是否全屏
 */
fun isFullScreen(activity: Activity?): Boolean {
    val fullScreenFlag = WindowManager.LayoutParams.FLAG_FULLSCREEN
    return if (activity == null) {
        false
    } else {
        activity.window.attributes.flags and fullScreenFlag == fullScreenFlag
    }
}

/**
 * Return whether screen is landscape.
 */
fun isLandscape(): Boolean {
    return appContext.resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE
}

/**
 * Return whether screen is portrait.
 */
fun isPortrait(): Boolean {
    return appContext.resources.configuration.orientation ==
            Configuration.ORIENTATION_PORTRAIT
}


