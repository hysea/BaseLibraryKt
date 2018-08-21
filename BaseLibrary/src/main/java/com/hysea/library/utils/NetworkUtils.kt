package com.hysea.library.utils

import android.content.Context
import android.net.ConnectivityManager
import android.telephony.TelephonyManager

/**
 * 网络相关工具类
 * Created by hysea on 2018/8/20.
 */

/**
 * 判断网络是否连接
 */
fun isConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    val info = cm?.activeNetworkInfo
    return info?.isConnected ?: false
}

/**
 * 判断网络连接方式是否为wifi
 */
fun isWifiConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    val info = cm?.activeNetworkInfo
    return info?.type == ConnectivityManager.TYPE_WIFI
}

/**
 * 判断网络连接方式是否为4g
 */
fun is4GConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    val info = cm?.activeNetworkInfo
    return info?.subtype == TelephonyManager.NETWORK_TYPE_LTE
}