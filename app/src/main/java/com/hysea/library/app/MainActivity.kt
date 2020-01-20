package com.hysea.library.app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hysea.library.utils.LogUtils
import com.hysea.library.utils.isAppInstall
import com.hysea.library.utils.tag

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toWebView(view: View) {
        LogUtils.d(tag(), "isInstall:${isAppInstall(this,"com.tencent.map")}")
        LogUtils.d(tag(), "isInstall:${isAppInstall(this,"com.tencent.mm")}")
    }
}
