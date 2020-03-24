package com.hysea.library

import com.hysea.library.utils.postDelayedOnQueue
import com.hysea.library.utils.postOnQueue
import org.junit.Test

/**
 * create by hysea on 2020/3/23
 */
class ExtTest {
    @Test
    fun testObservable() {
        postOnQueue {
            println("postOnQueue: ${Thread.currentThread().name}")
        }

        Thread.sleep(2000)
    }
}
