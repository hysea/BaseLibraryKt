package com.hysea.library.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hysea.library.constant.MemoryUnit

/**
 * 转换相关工具类
 * Created by hysea on 2018/8/24.
 */


/**
 * 字节转换成指定的单位
 */
fun byteToMemorySize(byteSize: Long, @MemoryUnit.Unit unit: Int): Double {
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
        byteSize < MemoryUnit.KB -> String.format("%.2fB", byteSize)
        byteSize < MemoryUnit.MB -> String.format("%.2fKB", byteSize.toDouble() / MemoryUnit.KB)
        byteSize < MemoryUnit.GB -> String.format("%.2fMB", byteSize.toDouble() / MemoryUnit.MB)
        else -> String.format("%.2fGB", byteSize.toDouble() / MemoryUnit.GB)
    }
}

fun <T> fromJsonObject(json: String, classOfT: Class<T>): T {
    return Gson().fromJson(json, classOfT)
}

fun <T> fromJsonArray(json: String): List<T> {
    return Gson().fromJson(json, object : TypeToken<List<T>>() {}.type)
}