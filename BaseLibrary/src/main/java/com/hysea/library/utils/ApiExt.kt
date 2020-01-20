package com.hysea.library.utils

import android.os.Build

/**
 * create by hysea on 2020/1/19
 */
inline fun toApi(toVersion: Int, inclusive: Boolean = false, action: () -> Unit) {
    if (Build.VERSION.SDK_INT < toVersion
            || (inclusive && Build.VERSION.SDK_INT == toVersion)) {
        action()
    }
}

inline fun fromApi(fromVersion: Int, inclusive: Boolean = true, action: () -> Unit) {
    if (Build.VERSION.SDK_INT > fromVersion
            || (inclusive && Build.VERSION.SDK_INT == fromVersion)) {
        action()
    }
}