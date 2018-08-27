package com.hysea.library.constant

import android.support.annotation.IntDef


/**
 * 常量
 * Created by hysea on 2018/8/20.
 */
open class Constant {
    companion object {
        const val FORMAT_Y_TO_S = "yyyy-MM-dd HH:mm:ss"
        const val FORMAT_Y_M_D = "yyyy-MM-dd"
        const val FORMAT_H_M = "HH:mm"
        const val FORMAT_H_M_S = "HH:mm:ss"

        const val KEY_WEB_TITLE = "web_title"
        const val KEY_WEB_URL = "web_url"

        /**
         * 默认退出app的间隔时间
         */
        const val DEFAULT_EXIT_INTERVAL: Long = 2000

        /**
         * 缓冲大小
         */
        const val BUFFER_SIZE = 1024 * 4


        const val BYTE = 1
        const val KB = 1024
        const val MB = 1048576
        const val GB = 1073741824
    }

    @IntDef(BYTE, KB, MB, GB)
    @Retention(AnnotationRetention.SOURCE)
    annotation class MemoryUnit
}