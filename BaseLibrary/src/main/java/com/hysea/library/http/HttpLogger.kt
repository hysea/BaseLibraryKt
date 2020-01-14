package com.hysea.library.http

import com.hysea.library.utils.LogUtils
import com.hysea.library.utils.tag
import okhttp3.logging.HttpLoggingInterceptor
import java.net.URLDecoder

/**
 * 网络请求日志打印拦截器：
 * 1、用于调试接口信息，
 * 2、通过它可以拦截网络请求和响应所有信息（请求行、请求头、请求体、响应行、响应头、响应体）
 */
class HttpLogger : HttpLoggingInterceptor.Logger {

    private val mMessage = StringBuffer()
    
    override fun log(message: String) {
        // 请求或者响应开始
        if (message.startsWith("--> POST") || message.startsWith("--> GET")) {
            mMessage.setLength(0)
        }
        val decodeMessage: String = try {
            // 将输出的信息utf-8解码
            URLDecoder.decode(message, "utf-8")
        } catch (ex: Exception) {
            ex.message ?: ""
        }
        mMessage.append(decodeMessage)
        mMessage.append("\n")
        // 响应结束，打印整条日志
        if (message.startsWith("<-- END HTTP")) {
            mMessage.append("---------------------------------------------------------\n")
            LogUtils.i(tag(), mMessage.toString())
        }
    }
}