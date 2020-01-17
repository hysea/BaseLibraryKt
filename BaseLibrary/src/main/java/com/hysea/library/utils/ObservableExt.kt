package com.hysea.library.utils

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * create by hysea on 2020/1/15
 */

fun <T> Observable<T>.applySchedules(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}


fun timerAction(delay: Long, action: (Long) -> Unit) =
        Observable.timer(delay, TimeUnit.SECONDS)
                .subscribe {
                    action.invoke(it)
                }

fun countdownAction(time: Long, action: (Long) -> Unit) =
        Observable.intervalRange(0, time + 1, 0, 1, TimeUnit.SECONDS)
                .applySchedules()
                .subscribe {
                    action.invoke(time - it)
                }