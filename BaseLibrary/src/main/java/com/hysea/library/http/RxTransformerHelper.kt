package com.hysea.library.http

import android.support.v7.app.AppCompatActivity
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * 线程转换帮助类
 * Created by hysea on 2018/6/28.
 */
object RxTransformerHelper {

    /**
     * 线程切换的Transformer
     */
    @JvmStatic
    fun <T> observableToMain(): ObservableTransformer<T, T> {

        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}