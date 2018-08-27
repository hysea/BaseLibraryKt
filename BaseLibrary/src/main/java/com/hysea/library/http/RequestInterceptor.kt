package com.hysea.library.http

import com.hysea.library.base.BaseApp
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * 参数请求拦截器
 * 用于添加参数，如token
 */
class RequestInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        val newBuilder = oldRequest.newBuilder()

        // 添加头部参数
        val header = BaseApp.instance.getHttpHeader()
        if (header.isNotEmpty()) {
            for ((k, v) in header) {
                newBuilder.addHeader(k, v)
            }
        }
        return chain.proceed(newBuilder.build())
    }
}
