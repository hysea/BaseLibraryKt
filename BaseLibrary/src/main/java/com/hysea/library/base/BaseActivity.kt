package com.hysea.library.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import com.gyf.barlibrary.ImmersionBar
import com.hysea.library.R
import com.hysea.library.interfaces.IBaseView
import com.hysea.library.manager.AppManager

/**
 * Activity基类
 * Created by hysea on 2018/6/27.
 */
abstract class BaseActivity : AppCompatActivity(), IBaseView {
    private var immersionBar: ImmersionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initSetup(savedInstanceState)
    }

    open fun initSetup(savedInstanceState: Bundle?) {
        AppManager.addActivity(this)
        immersionBar = initImmersionBar()
        immersionBar?.init()
        init(savedInstanceState)
        initEvent()
    }

    /**
     * 初始化沉浸式状态栏
     */
    open fun initImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
                .barColor(R.color.white) // 导航栏和状态栏均为白色
                .statusBarDarkFont(true) // 状态栏字体深色
                .fitsSystemWindows(true)
                .keyboardEnable(true)
                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }


    /**
     * 设置监听事件
     */
    open fun initEvent() {
    }

    /**
     * 显示加载进度
     */
    open fun showLoading() {

    }

    /**
     * 隐藏加载进度
     */
    open fun hideLoading() {

    }

    override fun onDestroy() {
        AppManager.removeActivity(this)
        immersionBar?.destroy() // 防止内存泄漏
        super.onDestroy()
    }
}
