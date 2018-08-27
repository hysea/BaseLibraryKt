package com.hysea.library.app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hysea.library.base.BaseWebActivity
import com.hysea.library.constant.Constant

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toWebView(view: View) {
        val intent = Intent(this,WebViewActivity::class.java)
        intent.putExtra(Constant.KEY_WEB_TITLE,"百度")
        intent.putExtra(Constant.KEY_WEB_URL,"")
        startActivity(intent)
    }
}
