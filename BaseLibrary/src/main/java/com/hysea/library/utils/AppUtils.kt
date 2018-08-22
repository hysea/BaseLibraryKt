package com.hysea.library.utils

import com.hysea.library.R
import com.hysea.library.base.BaseApp
import com.hysea.library.constant.Constant
import com.hysea.library.manager.AppManager

/**
 * Created by hysea on 2018/8/21.
 */

/**
 * 手机品牌：如Xiaomi
 */
val brand = android.os.Build.BRAND

/**
 * 手机型号：如MIX 2
 */
val model = android.os.Build.MODEL

/**
 * 手机系统版本：如8.0.0
 */
val systemVersion = android.os.Build.VERSION.RELEASE


private var firstClickTime: Long = 0

/**
 * 双击点击退出app
 */
fun doubleClickExit(timeInterval: Long = Constant.DEFAULT_EXIT_INTERVAL) {
    if (System.currentTimeMillis() - firstClickTime >= timeInterval) {
        BaseApp.instance.showToast(getString(R.string.double_click_exit))
        firstClickTime = System.currentTimeMillis()
    } else {
        AppManager.exitApp()
    }
}