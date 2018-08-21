package com.hysea.library.utils

import android.util.Log

/**
 * 日志相关工具类
 * Created by hysea on 2018/8/20.
 */
object LogUtils {
    /**
     * 控制日志输出
     */
    private var LOG_ENABLE: Boolean = true

    fun setLogEnable(enable: Boolean) {
        LOG_ENABLE = enable
    }

    fun v(msg: String) = v(tag = TAG(), msg = msg)
    fun v(tag: String, msg: String) {
        if (LOG_ENABLE) {
            Log.v(tag, msg)
        }
    }

    fun i(msg: String) = i(tag = TAG(), msg = msg)
    fun i(tag: String, msg: String) {
        if (LOG_ENABLE) {
            Log.i(tag, msg)
        }
    }

    fun d(msg: String) = d(tag = TAG(), msg = msg)
    fun d(tag: String, msg: String) {
        if (LOG_ENABLE) {
            Log.d(tag, msg)
        }
    }

    fun e(msg: String) = e(tag = TAG(), msg = msg)
    fun e(tag: String, msg: String) {
        if (LOG_ENABLE) {
            Log.e(tag, msg)
        }
    }
}
