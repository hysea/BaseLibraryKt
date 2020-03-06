package com.hysea.library.interfaces

import android.os.Bundle
import androidx.annotation.LayoutRes

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
     * 初始化相关事件
     */
    fun initEvent()
}