package com.hysea.library.utils

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper

/**
 * create by hysea on 2020/1/16
 */
object ThreadPools {

    private val thread = HandlerThread("ThreadPools").apply {
        start()
    }

    private val handler = Handler(thread.looper)
    private val mainHandler = Handler(Looper.getMainLooper())

    fun postOnUI(task: () -> Unit) {
        mainHandler.post(task)
    }

    fun postOnUIDelayed(delay: Long, task: () -> Unit) {
        mainHandler.postDelayed(task, delay)
    }

    fun postOnQueue(task: () -> Unit) {
        handler.post(task)
    }

    fun postOnQueue(delay: Long, task: () -> Unit) {
        handler.postDelayed(task, delay)
    }
}