package com.hysea.library.utils

import java.io.File
import java.io.FileInputStream
import java.nio.channels.FileChannel
import java.security.MessageDigest

/**
 * 加密相关工具类
 * Created by hysea on 2018/8/20.
 */

private const val ENCRYPT_STR = "0123456789abcdef"

/**
 * MD5加密字符串
 */
fun md5Encrypt(str: String?) {
    encrypt(str, "MD5")
}

/**
 * MD5加密文件
 */
fun md5Encrypt(file: File): ByteArray? {
    if (exists(file)) {
        var fis: FileInputStream? = null
        var channel: FileChannel? = null
        try {
            fis = FileInputStream(file)
            channel = fis.channel
            val buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length())
            val md = MessageDigest.getInstance("MD5")
            md.update(buffer)
            return md.digest()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            channel?.closeQuietly()
            fis?.closeQuietly()
        }
    }
    return null
}

/**
 * SHA512加密
 */
fun sha512Encrypt(str: String?) {
    encrypt(str, "SHA-512")
}

/**
 * 加密
 * @param str 加密字符串
 * @param encryptType 加密方式
 */
fun encrypt(str: String?, encryptType: String): String {
    if (str.isNullOrBlank()) {
        return ""
    }
   return encrypt(str.toByteArray(),encryptType)
}

fun encrypt(byteArray: ByteArray?, encryptType: String):String {
    if (byteArray == null || byteArray.isEmpty()) {
        return ""
    }
    return try {
        val bytes = MessageDigest
                .getInstance(encryptType)
                .digest(byteArray)
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(ENCRYPT_STR[i shr 4 and 0x0f])
            result.append(ENCRYPT_STR[i and 0x0f])
        }
        result.toString()
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}