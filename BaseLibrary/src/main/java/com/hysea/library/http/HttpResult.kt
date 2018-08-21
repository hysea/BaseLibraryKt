package com.hysea.library.http

/**
 * 返回的基类
 * Created by hysea on 2018/6/28.
 */
data class HttpResult<T>(var code: Int, var msg: String, var data: T?) {

    /**
     * 接口是否请求成功
     */
    fun isSuccess(): Boolean {
        return code == 0
    }
}