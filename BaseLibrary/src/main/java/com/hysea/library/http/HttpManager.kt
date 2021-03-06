package com.hysea.library.http

import com.hysea.library.BuildConfig
import com.hysea.library.base.BaseApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Http网络请求管理
 * Created by hysea on 2018/6/28.
 */
object HttpManager {
    private const val CONNECT_TIMEOUT = 15L
    private const val READ_TIMEOUT = 15L
    private const val WRITE_TIMEOUT = 15L

    private val mOkHttpClient: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                // 处理参数
                .addInterceptor(RequestHeaderInterceptor())

        if (BuildConfig.DEBUG) {
            // 打印日志
            val loggingInterceptor = HttpLoggingInterceptor(HttpLogger())
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }

        builder.build()

    }

    private val mRetrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BaseApp.instance.getBaseUrl())
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @JvmStatic
    fun <T : Any> createRequest(clazz: Class<T>): T {
        return mRetrofit.create(clazz)
    }
}