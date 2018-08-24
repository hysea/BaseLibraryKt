package com.hysea.library.interfaces

import android.os.Bundle
import android.support.annotation.LayoutRes

/**
 * Activity与Fragment的公共逻辑
 * Created by hysea on 2018/6/27.
 */
interface IBaseView {
    /**
     * 获取布局id
     */
    @LayoutRes
    fun getLayoutId(): Int

    /**
     * 相关初始化操作
     */
    fun init(savedInstanceState: Bundle?)

    /**
     * 跳转页面，无参数
     */
    fun <T : Any> readGo(clazz: Class<T>)

    /**
     * 跳转页面，带参数
     */
    fun <T : Any> readGo(clazz: Class<T>, bundle: Bundle?)

    /**
     * 跳转页面，无参数，并销毁该界面
     */
    fun <T : Any> readGoThenFinish(clazz: Class<T>)

    /**
     * 跳转页面，带参数，并销毁该界面
     */
    fun <T : Any> readGoThenFinish(clazz: Class<T>, bundle: Bundle?)

    /**
     * 跳转页面返回结果，无参数
     */
    fun <T : Any> readGoForResult(clazz: Class<T>, requestCode: Int)

    /**
     * 跳转页面返回结果，带参数
     */
    fun <T : Any> readGoForResult(clazz: Class<T>, bundle: Bundle?, requestCode: Int)
}