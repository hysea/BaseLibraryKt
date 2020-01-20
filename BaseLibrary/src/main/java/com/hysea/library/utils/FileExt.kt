package com.hysea.library.utils

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.text.TextUtils
import androidx.core.content.FileProvider
import com.hysea.library.BuildConfig
import java.io.File

/**
 * 文件相关工具类
 * Created by hysea on 2018/8/20.
 */


/** 判断文件是否存在，兼容文件为空的情况 */
fun File?.exists(): Boolean = this?.exists() ?: false

/** 判断file是否是文件 */
fun isFile(file: File?): Boolean = file.exists() && (file?.isFile ?: false)

/** 判断file是否是目录 */
fun isDir(file: File?): Boolean = file.exists() && (file?.isDirectory ?: false)

/** 确保文件目录存在 */
fun ensureDirectoryExists(dir: File): File {
    if (dir.exists() && dir.isDirectory) {
        return dir
    }

    // 防止有同名文件
    if (dir.exists() && dir.isFile) dir.delete()

    dir.mkdirs()
    return dir
}

fun ensureFileExists(file: File): File {
    if (file.exists() && file.isFile) {
        return file
    }
    file.createNewFile()
    return file
}

/** 获取缓存文件大小 */
fun Context.getCacheSize(excludes: (File) -> Boolean): Long {
    var cacheSize = cacheDir.getFileSize(excludes)
    if (checkSDCardAvailable()) {
        cacheSize += externalCacheDir?.getFileSize(excludes) ?: 0
    }
    return cacheSize
}

/** 清除缓存 */
fun Context.clearCache(includes: (File) -> Boolean) {
    cacheDir.listFiles(includes).forEach {
        it.deleteRecursively()
    }

    if (checkSDCardAvailable()) {
        externalCacheDir?.listFiles(includes)?.forEach {
            it.deleteRecursively()
        }
    }
}

/** 获取文件扩展名 */
fun String.getFileExt() = this.substringAfterLast(".")

/** 获取文件的名称，带扩展名 */
fun String.getFileNameWithExt() = this.substringAfterLast(File.separator)

/** 获取文件的名称，不带扩展名 */
fun String.getFileName() = this.getFileNameWithExt().substringBeforeLast(".")

/** 检查sd卡是否可用 */
private fun checkSDCardAvailable(): Boolean = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

/** 获取sd卡根目录 */
fun getSDCardDir(context: Context): String {
    return if (checkSDCardAvailable()) {
        Environment.getExternalStorageDirectory().absolutePath
    } else {
        getFileDir(context, null)
    }
}

/**
 * 获取文件大小
 * @param excludes 排除指定文件
 */
fun File.getFileSize(excludes: (File) -> Boolean): Long {
    if (excludes(this)) return 0

    return when {
        isFile -> length()
        isDirectory -> listFiles().fold(0L, { acc, file ->
            acc + file.getFileSize(excludes)
        })
        else -> 0
    }
}

/** 获取文件存储目录 */
fun getFileDir(context: Context, dir: String?): String {
    return if (checkSDCardAvailable()) {
        context.getExternalFilesDir(dir)!!.absolutePath
    } else {
        if (TextUtils.isEmpty(dir)) context.filesDir.absolutePath else context.filesDir.absolutePath + File.separator + dir
    }
}

fun File.saveBytesToFile(bytes: ByteArray, isAppendBytes: Boolean = false) {
    if (isAppendBytes) {
        this.appendBytes(bytes)
    } else {
        this.writeBytes(bytes)
    }
}

fun File.saveTextToFile(text: String, isAppendText: Boolean = false) {
    if (isAppendText) {
        this.appendText(text)
    } else {
        this.writeText(text)
    }
}

fun File.readFileToBytes(): ByteArray = this.readBytes()

fun File.readFileToText(): String = this.readText()


fun File.toUri(context: Context): Uri {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        val authority = "${BuildConfig.APPLICATION_ID}.provider"
        FileProvider.getUriForFile(context, authority, this)
    } else {
        Uri.fromFile(this)
    }
}


