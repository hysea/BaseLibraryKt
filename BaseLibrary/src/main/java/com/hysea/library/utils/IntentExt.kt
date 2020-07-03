package com.hysea.library.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.core.net.toUri

/**
 * create by hysea on 2020/1/20
 */


/**
 * 打开应用市场，如评分
 * @param packageName 应用的包名即ApplicationId
 * @param market 指定具体的应用商店
 */
fun launchAppMarket(context: Context, packageName: String, market: AppStore? = null, exceptionHandler: ((Exception) -> Unit)? = null) {
    val uri = Uri.parse("market://details?id=$packageName")
    try {
        Intent(Intent.ACTION_VIEW, uri).apply {
            this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            // 设置打开具体的应用市场
            if (market != null) this.setPackage(market.value)
            context.startActivity(this)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        exceptionHandler?.invoke(e)
    }
}

fun launchThirdApp(context: Context, uri: Uri, exceptionHandler: ((Exception) -> Unit)? = null) {
    try {
        Intent(Intent.ACTION_VIEW, uri).apply {
            this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(this)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        exceptionHandler?.invoke(e)
    }
}

enum class AppStore(val value: String) {
    /** Google Play */
    MARKET_GOOGLE_PALY("com.android.vending"),
    /** 应用宝 */
    MARKET_TENCENT("com.tencent.android.qqdownloader"),
    /** 360手机助手 */
    MARKET_QIHOO("com.qihoo.appstore"),
    /** 小米应用商店 */
    MARKET_XIAOMI("com.xiaomi.market"),
    /** 华为应用市场 */
    MARKET_HUAWEI("com.huawei.appmarket")
}