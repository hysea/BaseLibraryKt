package com.hysea.library.http.exception

/**
 * Http接口错误/异常统一处理
 * 异常=[程序异常、网络异常、解析异常...]
 * 错误=[接口逻辑错误，如-1001:token失效]
 * Created by hysea on 2018/6/28.
 */

data class ApiException(val code: Int, val msg: String?) : Exception(msg)