package com.hysea.library.utils

import android.content.Context
import android.os.Environment
import android.text.TextUtils
import com.hysea.library.base.BaseApp
import com.hysea.library.constant.Constant.Companion.BUFFER_SIZE
import java.io.*

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
 * 判断file是否是文件
 */
fun isFile(file: File?): Boolean = exists(file) && (file?.isFile ?: false)

/**
 * 判断file是否是目录
 */
fun isDir(file: File?): Boolean = exists(file) && (file?.isDirectory ?: false)

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

/**
 * 保存数据到本地文件
 * @param bytes 要保存的数据
 * @param filePath 文件保存的路径
 * @param fileName 文件名字
 */
fun saveFile(bytes: ByteArray?, filePath: String, fileName: String) {
    var bos: BufferedOutputStream? = null
    var fos: FileOutputStream? = null

    try {
        val dir = File(filePath)
        if (!dir.exists()) { // 判断文件目录是否存在
            dir.mkdirs()
        }

        // 如果文件存在则删除文件
        val file = File(filePath, fileName)
        if (file.exists()) {
            file.delete()
        }

        fos = FileOutputStream(file)
        bos = BufferedOutputStream(fos)
        bos.write(bytes)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        bos?.closeQuietly()
        fos?.closeQuietly()
    }
}

/**
 * 读取文件
 * @return 返回字节数组
 */
fun readFile(file: File?): ByteArray? {
    if (!exists(file)) return null
    try {
        return isToBytes(FileInputStream(file))
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
        return null
    }
}

/**
 * 将InputStream转换成ByteArray
 */
fun isToBytes(input: InputStream?): ByteArray? {
    if (input == null) return null
    var output: ByteArrayOutputStream? = null
    try {
        output = ByteArrayOutputStream()
        val bytes = ByteArray(BUFFER_SIZE)
        var len = 0

        // 使用use与also操作符
        output.use {
            while (input.read(bytes, 0, BUFFER_SIZE).also { len = it } != -1) {
                it.write(bytes, 0, len)
            }
        }
        // 一般写法
//        while ({ len = input.read(bytes, 0, BUFFER_SIZE);len }() != -1) {
//            output.write(bytes, 0, len)
//        }
        return output.toByteArray()
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    } finally {
        input.closeQuietly()
        output?.closeQuietly()
    }
}


