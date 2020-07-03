package com.hysea.library.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Point
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.WindowManager


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
 * 获取屏幕高度 -- 不包含导航栏(虚拟按键)
 */
fun getScreenHeight(context: Context): Int {
    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val outMetrics = DisplayMetrics()
    wm.defaultDisplay.getMetrics(outMetrics)
    return outMetrics.heightPixels
}

/**
 * 获取导航栏高度
 */
fun getNavBarHeight(): Int {
    val res = Resources.getSystem()
    val resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resourceId != 0) res.getDimensionPixelSize(resourceId) else 0
}


/**
 * 获取屏幕真实高度 -- 包含导航栏(虚拟按键)
 */
fun getScreenRealHeight(context: Context): Int {
    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val point = Point()
    wm.defaultDisplay.getRealSize(point)
    return point.y
}

/**
 * 导航栏是否显示
 */
fun isShowNavBar(context: Context?): Boolean {
    if (null == context) {
        return false
    }
    /**
     * 获取应用区域高度
     */
    val outRect1 = Rect()
    try {
        (context as Activity).window.decorView.getWindowVisibleDisplayFrame(outRect1)
    } catch (e: ClassCastException) {
        e.printStackTrace()
        return false
    }
    val activityHeight = outRect1.height()

    /**
     * 屏幕物理高度 - 状态栏高度  导航栏高度 = 导航栏存在时的应用实际高度
     */
    val remainHeight = getScreenRealHeight(context) - getStatusBarHeight() - getNavBarHeight()
    /**
     * 剩余高度跟应用区域高度相等 说明导航栏存在
     */
    return activityHeight == remainHeight
}

/**
 * 获取状态栏高度
 */
fun getStatusBarHeight(): Int {
    val resources = Resources.getSystem()
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return resources.getDimensionPixelSize(resourceId)
}

/**
 * 设置全屏
 */
fun setFullScreen(activity: Activity?) {
    activity?.window?.addFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN or
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
}

/**
 * 设置非全屏
 */
fun setNonFullScreen(activity: Activity?) {
    activity?.window?.clearFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN
                    or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
}


/**
 * 全屏与非全屏切换
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
 * 是否横屏
 */
fun isLandscape(context: Context): Boolean {
    return context.resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE
}

/**
 * 是否竖屏
 */
fun isPortrait(context: Context): Boolean {
    return context.resources.configuration.orientation ==
            Configuration.ORIENTATION_PORTRAIT
}

