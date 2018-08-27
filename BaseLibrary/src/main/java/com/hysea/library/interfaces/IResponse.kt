package com.hysea.library.interfaces

import com.hysea.library.http.exception.ApiException


/**
 * 响应回调接口
 * Created by hysea on 2018/6/28.
 */
interface IResponse<T> {
    /**
     * 开始请求，主要是用于进度条加载显示
     */
    fun onStart()

    /**
     * 请求成功
     */
    fun onSuccess(t: T?)

    /**
     * 请求失败
     */
    fun onFailure(ex: ApiException)
}