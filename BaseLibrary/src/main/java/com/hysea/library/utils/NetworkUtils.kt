package com.hysea.library.utils

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.annotation.RequiresPermission
import android.telephony.TelephonyManager
import com.hysea.library.base.BaseApp
import com.hysea.library.constant.NetworkType


/**
 * 网络相关工具类
 * Created by hysea on 2018/8/20.
 */

/**
 * 判断网络是否连接
 */
fun isConnected(context: Context): Boolean {
    val info = getActiveNetworkInfo()
    return info?.isConnected ?: false
}

/**
 * 判断网络连接方式是否为wifi
 */
fun isWifiConnected(context: Context): Boolean {
    val info = getActiveNetworkInfo()
    return info?.type == ConnectivityManager.TYPE_WIFI
}

/**
 * 判断网络连接方式是否为4g
 */
fun is4GConnected(context: Context): Boolean {
    val info = getActiveNetworkInfo()
    return info?.subtype == TelephonyManager.NETWORK_TYPE_LTE
}

/**
 * 获取NetworkInfo
 */
@RequiresPermission(ACCESS_NETWORK_STATE)
private fun getActiveNetworkInfo(): NetworkInfo? {
    val cm = BaseApp.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    return cm?.activeNetworkInfo
}

/**
 * 获取网络状态类型
 */
@RequiresPermission(ACCESS_NETWORK_STATE)
fun getNetworkType(): NetworkType {
    var netType = NetworkType.NETWORK_NO
    getActiveNetworkInfo()?.let {
        if (it.isAvailable) {
            when {
                it.type == ConnectivityManager.TYPE_ETHERNET -> netType = NetworkType.NETWORK_ETHERNET
                it.type == ConnectivityManager.TYPE_WIFI -> netType = NetworkType.NETWORK_WIFI
                it.type == ConnectivityManager.TYPE_MOBILE -> when (it.subtype) {
                    TelephonyManager.NETWORK_TYPE_GSM, TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN -> netType = NetworkType.NETWORK_2G
                    TelephonyManager.NETWORK_TYPE_TD_SCDMA, TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP -> netType = NetworkType.NETWORK_3G
                    TelephonyManager.NETWORK_TYPE_IWLAN, TelephonyManager.NETWORK_TYPE_LTE -> netType = NetworkType.NETWORK_4G
                    else -> {
                        val subtypeName = it.subtypeName
                        if (subtypeName.equals("TD-SCDMA", ignoreCase = true)
                                || subtypeName.equals("WCDMA", ignoreCase = true)
                                || subtypeName.equals("CDMA2000", ignoreCase = true)) {
                            netType = NetworkType.NETWORK_3G
                        } else {
                            netType = NetworkType.NETWORK_UNKNOWN
                        }
                    }
                }
                else -> netType = NetworkType.NETWORK_UNKNOWN
            }
        }
    }
    return netType
}