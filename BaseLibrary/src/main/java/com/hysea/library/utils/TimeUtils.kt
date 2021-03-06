package com.hysea.library.utils

import com.hysea.library.constant.Constants
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * 时间相关工具类
 * Created by hysea on 2018/8/20.
 */


const val FORMAT_Y_TO_S = "yyyy-MM-dd HH:mm:ss"
const val FORMAT_Y_M_D = "yyyy-MM-dd"
const val FORMAT_H_M = "HH:mm"
const val FORMAT_H_M_S = "HH:mm:ss"

/**
 * 格式化时长
 * @param duration 单位秒
 */
fun formatDuration(duration: Long): String {
    val minute = duration / 60
    val second = duration % 60
    return if (minute / 60 > 0) {
        String.format("%02d:%02d:%02d", minute / 60, minute % 60, second)
    } else {
        String.format("%02d:%02d", minute, second)
    }
}

/**
 * 格式化时间
 * @param timeStamp 时间戳，单位ms
 * @param format 格式，默认为：yyyy-MM-dd HH:mm:ss
 */
fun formatTime(timeStamp: Long, format: String = FORMAT_Y_TO_S): String {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.format(timeStamp)
}

/**
 * 转化为时间戳
 * @param time 时间字符串
 * @param format 时间字符串对应的格式类型
 */
fun convertTimeStamp(time: String, format: String):Long {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    try {
        return dateFormat.parse(time).time
    } catch (ex: ParseException) {
        ex.printStackTrace()
    }
    return -1
}

