package com.hysea.library.base

import android.app.Application
import com.hysea.library.BuildConfig
import com.hysea.library.utils.LogUtils
import com.hysea.library.utils.saveFile
import com.squareup.leakcanary.LeakCanary
import kotlin.properties.Delegates

/**
 * Application基类
 * Created by hysea on 2018/8/20.
 */
class BaseApp : Application() {

    companion object {
        var instance: BaseApp by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        LogUtils.setLogEnable(BuildConfig.DEBUG)
        initLeakCanary()
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
}