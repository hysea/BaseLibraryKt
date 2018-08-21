package com.hysea.library.utils

import java.security.MessageDigest

/**
 * 加密相关工具类
 * Created by hysea on 2018/8/20.
 */

const val ENCRYPT_STR = "0123456789abcdef"

/**
 * MD5加密
 */
fun md5Encrypt(str: String?) {
    encrypt(str, "MD5")
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

    return try {
        val bytes = MessageDigest
                .getInstance(encryptType)
                .digest(str?.toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(ENCRYPT_STR[i shr 4 and 0x0f])
            result.append(ENCRYPT_STR[i and 0x0f])
        }
        result.toString().toLowerCase()
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}