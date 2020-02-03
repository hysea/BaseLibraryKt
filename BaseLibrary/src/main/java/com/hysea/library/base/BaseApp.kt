package com.hysea.library.base

import android.app.Application
import com.hysea.library.BuildConfig
import com.hysea.library.utils.LogUtils
import com.tencent.bugly.crashreport.CrashReport
import kotlin.properties.Delegates

/**
 * Application基类
 * Created by hysea on 2018/8/20.
 */
abstract class BaseApp : Application() {

    companion object {
        var instance: BaseApp by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        LogUtils.setLogEnable(BuildConfig.DEBUG)
        initBugly()
    }


    private fun initBugly() {
        CrashReport.initCrashReport(this, "", BuildConfig.DEBUG)
        CrashReport.setAppVersion(this, "${BuildConfig.VERSION_NAME}-${BuildConfig.VERSION_CODE}-${if (BuildConfig.DEBUG) "DEBUG" else "RELEASE"}")
    }

    /**
     * 获取http请求的BaseUrl
     */
    abstract fun getBaseUrl(): String


    /**
     * 获取Http请求的公共的头部参数
     */
    open fun getHttpHeader(): Map<String, String> {
        return HashMap()
    }
}