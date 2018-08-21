package com.hysea.library.http

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
//        if (token.isNotEmpty()) {
//            newBuilder.addHeader(Constant.AUTH_TOKEN, token)
//        }
        return chain.proceed(newBuilder.build())
    }
}
