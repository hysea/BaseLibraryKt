package com.hysea.library.constant

import androidx.annotation.IntDef


/**
 * 常量
 * Created by hysea on 2018/8/20.
 */
open class Constants {
    companion object {
        const val FORMAT_Y_TO_S = "yyyy-MM-dd HH:mm:ss"
        const val FORMAT_Y_M_D = "yyyy-MM-dd"
        const val FORMAT_H_M = "HH:mm"
        const val FORMAT_H_M_S = "HH:mm:ss"

        /**
         * 默认退出app的间隔时间
         */
        const val DEFAULT_EXIT_INTERVAL: Long = 2000

        /**
         * 缓冲大小
         */
        const val BUFFER_SIZE = 1024 * 4

        const val NONE = -1
    }
}