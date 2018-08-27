package com.hysea.library.base

import android.app.Application
import com.hysea.library.BuildConfig
import com.hysea.library.utils.LogUtils
import com.squareup.leakcanary.LeakCanary
import com.tencent.smtt.sdk.QbSdk
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
        initLeakCanary()
        initX5()
    }

    /**
     * 内存泄漏检测
     */
    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

    /**
     * 初始化X5内核
     */
    private fun initX5() {
        QbSdk.initX5Environment(this, object : QbSdk.PreInitCallback {
            override fun onCoreInitFinished() {
                //x5内核初始化完成回调接口，此接口回调并表示已经加载起来了x5，有可能特殊情况下x5内核加载失败，切换到系统内核。
            }

            override fun onViewInitFinished(b: Boolean) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                LogUtils.i("加载内核是否成功:$b")
            }
        })
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