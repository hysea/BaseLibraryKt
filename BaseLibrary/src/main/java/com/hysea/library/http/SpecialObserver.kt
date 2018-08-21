package com.hysea.library.http

import com.hysea.library.R
import com.hysea.library.base.BaseApp
import com.hysea.library.http.exception.ApiException
import com.hysea.library.http.exception.ServerException
import com.hysea.library.interfaces.IResponse
import com.hysea.library.utils.getString
import com.hysea.library.utils.isConnected
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by hysea on 2018/7/6.
 */
abstract class SpecialObserver<T> : Observer<T>, IResponse<T> {
    override fun onSubscribe(d: Disposable) {
        if (!isConnected(BaseApp.instance)) {
            // 没有网络
            val ex = ServerException(ExceptionHandler.NOT_NETWORK_ERROR, getString(R.string.not_network))
            onFailure(ExceptionHandler.handleException(ex))
            d.dispose()
        }
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onComplete() {
    }

    override fun onError(e: Throwable) {
        val exception = ExceptionHandler.handleException(e)
        onFailure(exception)
    }

    override fun onFailure(ex: ApiException) {

    }
}
