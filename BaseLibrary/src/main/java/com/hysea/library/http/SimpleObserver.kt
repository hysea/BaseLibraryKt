package com.hysea.library.http

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by hysea on 2018/6/28.
 */
class SimpleObserver<T> : Observer<T> {
    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
    }
}