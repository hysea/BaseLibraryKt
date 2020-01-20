package com.hysea.library.constant

import androidx.annotation.IntDef

/**
 * 内存相关单位
 * create by hysea on 2020/1/20
 */
object MemoryUnit {
    const val BYTE = 1
    const val KB = 1024
    const val MB = 1048576
    const val GB = 1073741824

    @IntDef(BYTE, KB, MB, GB)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Unit
}