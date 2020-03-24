package com.hysea.library.utils

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * create by hysea on 2020/1/15
 */

fun <T> Observable<T>.applySchedules(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}


fun timerAction(
    delay: Long,
    unit: TimeUnit = TimeUnit.SECONDS,
    action: (Long) -> Unit
): Disposable =
    Observable.timer(delay, unit)
        .subscribe {
            action.invoke(it)
        }

fun intervalAction(
    interval: Long,
    unit: TimeUnit = TimeUnit.SECONDS,
    action: (Long) -> Unit
): Disposable =
    Observable.interval(interval, unit)
        .applySchedules()
        .subscribe {
            action.invoke(it)
        }

fun countdownAction(
    time: Long,
    unit: TimeUnit = TimeUnit.SECONDS,
    action: (Long) -> Unit
): Disposable =
    Observable.intervalRange(0, time + 1, 0, 1, unit)
        .applySchedules()
        .subscribe {
            action.invoke(time - it)
        }

fun postOnQueue(delay: Long = 0, action: () -> Unit): Disposable {
    return Observable.timer(delay, TimeUnit.MILLISECONDS)
        .observeOn(Schedulers.io())
        .subscribe {
            action.invoke()
        }
}

fun postOnUI(delay: Long = 0, action: () -> Unit): Disposable {
    return Observable.timer(delay, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            action.invoke()
        }
}