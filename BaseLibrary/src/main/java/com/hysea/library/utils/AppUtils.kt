package com.hysea.library.utils

import com.hysea.library.R
import com.hysea.library.base.BaseApp
import com.hysea.library.constant.Constant

/**
 * @create @author hysea 2018/8/21
 * @change @author hysea on 2020/1/14
 */

/**
 * 手机品牌：如Xiaomi
 */
inline val brand
    get() = android.os.Build.BRAND

/**
 * 手机型号：如MIX 2
 */
inline val model
    get() = android.os.Build.MODEL

/**
 * 手机系统版本：如8.0.0
 */
inline val systemVersion
    get() = android.os.Build.VERSION.RELEASE

inline val sdkVersion
    get() = android.os.Build.VERSION.SDK_INT


private var firstClickTime: Long = 0

/**
 * 双击点击退出app
 */
fun doubleClickExit(timeInterval: Long = Constant.DEFAULT_EXIT_INTERVAL, block: () -> Unit) {
    if (System.currentTimeMillis() - firstClickTime >= timeInterval) {
        BaseApp.instance.showToast(R.string.double_click_exit)
        firstClickTime = System.currentTimeMillis()
    } else {
        block()
    }
}