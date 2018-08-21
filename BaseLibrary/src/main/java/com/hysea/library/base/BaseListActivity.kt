package com.hysea.library.base

import android.os.Bundle
import com.foundao.likevideo.base.BaseActivity
import com.hysea.library.R

/**
 * 列表基础Activity
 * Created by hysea on 2018/8/21.
 */
class BaseListActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_base_list

    override fun init(savedInstanceState: Bundle?) {
    }

}