package com.hysea.library.http.exception
/**
 * Created by hysea on 2018/6/28.
 */
class ServerException(
        /**
         * 错误码
         */
        val code: Int,
        /**
         * 错误信息
         */
        val msg: String) : RuntimeException()
