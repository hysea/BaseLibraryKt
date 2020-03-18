package com.hysea.library.interfaces

/**
 * create by hysea on 2020/3/18
 */
interface IHttpResult<T> {

    fun isSuccessful(): Boolean

    fun getHttpResult(): T?

    fun getHttpMsg(): String?
}