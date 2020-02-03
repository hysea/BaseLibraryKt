package com.hysea.library.http

import okhttp3.Interceptor

/**
 * create by hysea on 2020/1/21
 */
class RequestConfig {

    /**
     * 设置BaseUrl
     */
    fun setBaseUrl(baseUrl: String) {

    }


    fun setTimeout(timeout: Long) {
        setReadTimeout(timeout)
        setWriteTimeout(timeout)
        setConnectTimeout(timeout)
    }

    fun addInterceptor(interceptor: Interceptor) {

    }

    /**
     * 设置全局读取超时时间
     */
    fun setReadTimeout(readTimeout: Long) {

    }

    /**
     * 设置全局写入超时时间
     */
    fun setWriteTimeout(writeTimeout: Long) {

    }

    /**
     * 设置全局连接超时时间
     */
    fun setConnectTimeout(connectTimeout: Long) {

    }
}