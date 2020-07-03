package com.hysea.library.utils

import android.content.res.TypedArray
import android.graphics.Bitmap
import androidx.core.content.res.use

/**
 * create by hysea on 2020/1/19
 */

//inline fun <T : TypedArray?, R> T.use(block: (T) -> R): R {
//    var recycled = false
//    try {
//        return block(this)
//    } catch (e: Exception) {
//        recycled = true
//        this?.recycle()
//        throw e
//    } finally {
//        if (!recycled) {
//            this?.recycle()
//        }
//    }
//}

inline fun <R> TypedArray.use(block: (TypedArray) -> R): R {
    return block(this).also {
        recycle()
    }
}

inline fun <R> Bitmap.use(block: (Bitmap) -> R): R {
    return block(this).also {
        recycle()
    }
}

//inline fun <T : Bitmap?, R> T.use(block: (T) -> R): R {
//    var recycled = false
//    try {
//        return block(this)
//    } catch (e: Exception) {
//        recycled = true
//        this?.recycle()
//        throw e
//    } finally {
//        if (!recycled) {
//            this?.recycle()
//        }
//    }
//}