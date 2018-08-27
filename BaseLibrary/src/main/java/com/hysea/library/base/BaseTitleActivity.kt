package com.hysea.library.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import com.hysea.library.R
import com.hysea.library.utils.inflateLayout
import com.hysea.library.view.TitleBar
import kotlinx.android.synthetic.main.activity_base_title.*

/**
 * 带Title的基础Activity
 * Created by hysea on 2018/8/21.
 */
abstract class BaseTitleActivity : BaseActivity() {

    final override fun getLayoutId(): Int = R.layout.activity_base_title


    final override fun initSetup(savedInstanceState: Bundle?) {
        inflateLayout(getContentView(), mContentLayout, true)
        super.initSetup(savedInstanceState)
        setupTitleBar(mTitleBar)
    }

    /**
     * 获取内容布局
     */
    @LayoutRes
    abstract fun getContentView(): Int

    /**
     * 设置TitleBar
     */
    abstract fun setupTitleBar(titleBar: TitleBar)
}