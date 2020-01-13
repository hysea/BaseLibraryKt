package com.hysea.library.app

import com.hysea.library.base.BaseWebActivity
import com.hysea.library.utils.getResColor
import com.hysea.library.view.TitleBar

/**
 * Created by hysea on 2018/8/27.
 */
class WebViewActivity : BaseWebActivity() {
    override fun setupTitleBar(titleBar: TitleBar) {
        titleBar.setTitle(mTitle)
        titleBar.setTitleTextColor(mContext.getResColor(android.R.color.holo_red_dark))
    }

}