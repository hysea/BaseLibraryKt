package com.hysea.library.interfaces

import com.hysea.library.http.exception.ApiException


/**
 * 响应回调接口
 * Created by hysea on 2018/6/28.
 */
interface IResponse<T> {
    fun onSuccess(t: T?)

    fun onFailure(ex: ApiException)
}