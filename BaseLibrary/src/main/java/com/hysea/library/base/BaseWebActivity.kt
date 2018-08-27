package com.hysea.library.base

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import com.hysea.library.R
import com.hysea.library.constant.Constant
import com.hysea.library.utils.showToast
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_base_title.*
import kotlinx.android.synthetic.main.activity_webview.*

/**
 * WebView展示页面，使用X5内核
 * Created by hysea on 2018/8/21.
 */
abstract class BaseWebActivity : BaseTitleActivity() {
    private var mUrl: String = ""
    protected var mTitle: String = ""

    /**
     * 当前页面Url，用于防止页面重定向
     */
    private var mCurrentUrl: String = ""

    override fun getContentView(): Int = R.layout.activity_webview

    @SuppressLint("SetJavaScriptEnabled")
    override fun init(savedInstanceState: Bundle?) {
        mUrl = intent.getStringExtra(Constant.KEY_WEB_URL)
        mTitle = intent.getStringExtra(Constant.KEY_WEB_TITLE)
        setupTitleBar(mTitleBar)

        if (mUrl.isBlank()) {
            showToast(getString(R.string.web_url_empty))
            return
        }

        mWebView.settings.javaScriptEnabled = true
        mWebView.settings.userAgentString = mWebView.settings.userAgentString + getString(R.string.app_name)
        mWebView.settings.builtInZoomControls = true// 隐藏缩放按钮
        mWebView.settings.useWideViewPort = true// 可任意比例缩放
        mWebView.settings.loadWithOverviewMode = true
        mWebView.settings.domStorageEnabled = true
        // 自适应屏幕
        mWebView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        mWebView.settings.pluginState = WebSettings.PluginState.ON
        mWebView.loadUrl(mUrl)

        setWebViewClient()
        setWebChromeClient()
    }


    open fun setWebViewClient() {
        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    // 防止重定向
                    if (mCurrentUrl.isNotEmpty() && mCurrentUrl == url) {
                        webView.loadUrl(url)
                        return true
                    }
                }
                return false
            }

            override fun onPageStarted(webView: WebView, url: String, bitmap: Bitmap) {
                super.onPageStarted(webView, url, bitmap)
                mCurrentUrl = url
                showLoading()
            }

            override fun onPageFinished(webView: WebView?, url: String?) {
                super.onPageFinished(webView, url)
                hideLoading()
            }
        }
    }

    open fun setWebChromeClient() {
        mWebView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (newProgress == 100) {
                    hideLoading()
                }
                super.onProgressChanged(view, newProgress)
            }
        }
    }

    open fun back() {
        if (mWebView.canGoBack()) {
            mWebView.goBack()
        } else {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        mWebView.resumeTimers()
    }

    override fun onPause() {
        super.onPause()
        mWebView.pauseTimers()
    }

    override fun onDestroy() {
        mWebView.destroy()
        super.onDestroy()
    }
}