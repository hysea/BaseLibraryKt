package com.hysea.library.utils

import java.io.Closeable
import java.io.IOException

/**
 * Created by hysea on 2018/8/21.
 */

/**
 * Closeable的扩展函数，静默关闭IO
 */
fun Closeable?.closeQuietly() {
    try {
        this?.close()
    } catch (ex: IOException) {
        ex.printStackTrace()
    }
}

/**
 * 关闭多个IO流
 */
fun closeIO(vararg closeables: Closeable) {
    closeables.forEach {
        it.closeQuietly()
    }
}