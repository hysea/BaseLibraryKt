package com.hysea.library.utils

import android.content.Context
import android.os.Environment
import android.text.TextUtils
import com.hysea.library.base.BaseApp
import java.io.File

/**
 * 文件相关工具类
 * Created by hysea on 2018/8/20.
 */

/**
 * 判断文件是否存在
 */
fun exists(file: File?): Boolean = file?.exists() ?: false

/**
 * 判断文件路径是否存在
 */
fun exists(filePath: String?): Boolean = filePath != null && exists(File(filePath))

/**
 * 检查sd卡是否可用
 */
private fun checkSDCardAvailable(): Boolean = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

/**
 * 获取sd卡根目录
 */
fun getSDCardDir(): String {
    return if (checkSDCardAvailable()) {
        Environment.getExternalStorageDirectory().absolutePath
    } else {
        getFileDir(BaseApp.instance, null)
    }
}

/**
 * 获取文件存储目录
 */
fun getFileDir(context: Context, dir: String?): String {
    return if (checkSDCardAvailable()) {
        context.getExternalFilesDir(dir).absolutePath
    } else {
        if (TextUtils.isEmpty(dir))
            context.filesDir.absolutePath
        else
            context.filesDir.absolutePath + File.separator + dir
    }
}
