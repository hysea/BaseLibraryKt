package com.hysea.library.http

import ExceptionHandler
import com.hysea.library.R
import com.hysea.library.base.BaseApp
import com.hysea.library.http.exception.ApiException
import com.hysea.library.interfaces.IResponse
import com.hysea.library.utils.getString
import com.hysea.library.utils.isConnected
import com.hysea.library.utils.showToast
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 * BaseObserver
 * Created by hysea on 2018/6/28.
 */
abstract class BaseObserver<T>(var isToast: Boolean = true) : Observer<HttpResult<T>>, IResponse<T?> {
    /**
     * 服务器返回的消息提示
     */
    val messageTip: String = ""

    final override fun onSubscribe(d: Disposable) {
        if (!isConnected(BaseApp.instance)) {
            // 没有网络
            val ex = ApiException(ExceptionHandler.NOT_NETWORK_ERROR, getString(R.string.not_network))
            onFailure(ex)
            d.dispose()
        }
    }

    override fun onNext(t: HttpResult<T>) {
        if (t.isSuccess()) { // 请求成功
            onSuccess(t.data)
        } else {
            val ex = ApiException(t.code, t.msg)
            onFailure(ex)
        }
    }

    override fun onComplete() {
    }

    final override fun onError(e: Throwable) {
        val exception = ExceptionHandler.handleException(e)
        onFailure(exception)
    }

    override fun onFailure(ex: ApiException) {
        if (isToast) {
            BaseApp.instance.showToast(ex.msg)
        }
    }
}