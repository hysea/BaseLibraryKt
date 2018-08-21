package com.hysea.library.manager

import android.app.Activity
import java.util.*

/**
 * App Activity的管理
 * Created by hysea on 2018/8/20.
 */

object AppManager {
    /**
     * 保存Activity对象的堆栈
     */
    private val mActivityStack: Stack<Activity> = Stack()

    /**
     * 添加Activity对象到堆栈
     */
    fun addActivity(activity: Activity) {
        mActivityStack.add(activity)
    }

    /**
     * 将Activity从堆栈中移除
     */
    fun removeActivity(activity: Activity) {
        if (mActivityStack.contains(activity)) {
            mActivityStack.remove(activity)
        }
    }

    /**
     * 结束指定的activity
     */
    fun finishActivity(activity: Activity) {
        if (mActivityStack.contains(activity)) {
            removeActivity(activity)
            activity.finish()
        }
    }

    /**
     * 获取当前Activity，即堆栈中最后一个压入的，栈顶
     */
    fun getCurrentActivity(): Activity? {
        return try {
            mActivityStack.lastElement()
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    /**
     * 返回指定的activity
     */
    fun <T> returnToActivity(clazz: Class<T>) {
        while (mActivityStack.size != 0) {
            val activity = mActivityStack.peek()
            if (activity.javaClass === clazz) {
                break
            } else {
                finishActivity(activity)
            }
        }
    }

    /**
     * 结束所有activity
     */
    fun finishAllActivity() {
        mActivityStack.forEach {
            finishActivity(it)
        }
        mActivityStack.clear()
    }

    /**
     * 退出app
     */
    fun exitApp() {
        try {
            finishAllActivity()
            // 退出JVM(java虚拟机),释放所占内存资源,0表示正常退出(非0的都为异常退出)
            System.exit(0)
            // 从操作系统中结束掉当前程序的进程
            android.os.Process.killProcess(android.os.Process.myPid())
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}