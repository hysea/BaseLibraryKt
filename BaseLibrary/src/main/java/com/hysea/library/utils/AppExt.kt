package com.hysea.library.utils

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Build
import com.hysea.library.constant.Constants
import java.io.File

/**
 * App相关扩展工具类
 * create by hysea on 2020/1/20
 */

/**
 * 手机品牌：如Xiaomi
 */
inline val brand
    get() = Build.BRAND

/**
 * 手机型号：如MIX 2
 */
inline val model
    get() = Build.MODEL

/**
 * 手机系统版本：如8.0.0
 */
inline val systemVersion
    get() = Build.VERSION.RELEASE

inline val sdkVersion
    get() = Build.VERSION.SDK_INT


private var firstClickTime: Long = 0
/** 双击点击退出app */
fun doubleClickExit(timeInterval: Long = Constants.DEFAULT_EXIT_INTERVAL, tipBlock: () -> Unit, exitBlock: () -> Unit) {
    if (System.currentTimeMillis() - firstClickTime >= timeInterval) {
        firstClickTime = System.currentTimeMillis()
        tipBlock()
    } else {
        exitBlock()
    }
}

/**
 * App是否安装
 */
fun isAppInstall(context: Context, packageName: String, exceptionHandler: ((Exception) -> Unit)? = null): Boolean {
    if (packageName.isBlank()) return false
    try {
        context.packageManager.getInstalledPackages(0).forEach {
            if (it.packageName == packageName) {
                return true
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        exceptionHandler?.invoke(e)
    }
    return false
}

/**
 * 安装App应用
 */
fun installApp(context: Context, file: File) {
    Intent(Intent.ACTION_VIEW).apply {
        this.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        this.setDataAndType(file.toUri(context), "application/vnd.android.package-archive")
        context.startActivity(this)
    }
}

fun Context.getAppInfo(): AppInfo? {
    val pi = this.packageManager?.getPackageInfo(this.applicationContext.packageName, 0)
    return if (pi == null) null else createAppInfo(this.packageManager, pi)
}


private fun createAppInfo(pm: PackageManager, pi: PackageInfo): AppInfo {
    val ai = pi.applicationInfo
    val name = ai.loadLabel(pm).toString()
    val icon = ai.loadLogo(pm)
    return AppInfo(
            name = name,
            icon = icon,
            packageName = pi.packageName,
            versionName = pi.versionName,
            versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                pi.longVersionCode
            } else {
                pi.versionCode.toLong()
            }
    )
}

/**
 * 判断App是否处于前台
 */
fun isAppForeground(context: Context): Boolean {
    val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager
    manager?.runningAppProcesses?.forEach {
        if (it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
            return it.processName == context.packageName
        }
    }
    return false
}

data class AppInfo(
        /** 名称 */
        val name: String,
        /** 图标 */
        val icon: Drawable,
        /** 包名 */
        val packageName: String,
        /** 版本号 */
        val versionName: String,
        /** 版本Code */
        val versionCode: Long
)