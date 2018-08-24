package com.hysea.library.utils

import android.content.Context
import com.hysea.library.constant.Constant

/**
 * 转换相关工具类
 * Created by hysea on 2018/8/24.
 */

/**
 * 将px转换成dp
 */
fun px2dp(context: Context, pxValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

/**
 * 将dp转换成px
 */
fun dp2px(context: Context, dpValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

/**
 * 将px转换成sp
 */
fun px2sp(context: Context, pxValue: Float): Int {
    val fontScale = context.resources.displayMetrics.scaledDensity
    return (pxValue / fontScale + 0.5f).toInt()
}

/**
 * 将sp转换成px
 */
fun sp2px(context: Context, spValue: Float): Int {
    val fontScale = context.resources.displayMetrics.scaledDensity
    return (spValue * fontScale + 0.5f).toInt()
}

/**
 * 字节转换成指定的单位
 */
fun byteToMemorySize(byteSize: Long, @Constant.MemoryUnit unit: Int): Double {
    if (byteSize < 0) return 0.0
    return (byteSize / unit).toDouble()
}

/**
 * 字节转换成合适的单位
 * 保留两位小数
 */
fun byteToFitMemorySize(byteSize: Long): String {
    return when {
        byteSize < 0 -> "shouldn't be less than zero!"
        byteSize < Constant.KB -> String.format("%.2fB", byteSize)
        byteSize < Constant.MB -> String.format("%.2fKB", byteSize.toDouble() / Constant.KB)
        byteSize < Constant.GB -> String.format("%.2fMB", byteSize.toDouble() / Constant.MB)
        else -> String.format("%.2fGB", byteSize.toDouble() / Constant.GB)
    }
}