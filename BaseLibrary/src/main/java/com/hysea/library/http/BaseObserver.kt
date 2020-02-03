package com.hysea.library.http

import ExceptionHandler
import com.hysea.library.R
import com.hysea.library.base.BaseApp
import com.hysea.library.http.exception.ApiException
import com.hysea.library.interfaces.IResponse
import com.hysea.library.utils.isConnected
import com.hysea.library.utils.showToast
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 * BaseObserver
 * Created by hysea on 2018/6/28.
 */
abstract class BaseObserver<T> : Observer<HttpResult<T>>, IResponse<T?> {


    final override fun onSubscribe(d: Disposable) {
//        if (!isConnected(BaseApp.instance)) {
//            // 没有网络
//            val ex = ApiException(ExceptionHandler.NOT_NETWORK_ERROR, BaseApp.instance.getString(R.string.not_network))
//            onFailure(ex)
//            d.dispose()
//        }
        onStart()
    }

    final override fun onNext(t: HttpResult<T>) {
        if (t.isSuccess()) { // 请求成功
            onSuccess(t.data)
        } else {
            val ex = ApiException(t.code, t.msg)
            onFailure(ex)
        }
    }

    final override fun onError(e: Throwable) {
        val exception = ExceptionHandler.handleException(e)
        onFailure(exception)
    }

    override fun onStart() {}

    override fun onComplete() {}

    override fun onFailure(ex: ApiException) {}
}