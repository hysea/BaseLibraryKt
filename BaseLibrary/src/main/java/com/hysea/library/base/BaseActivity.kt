package com.hysea.library.base

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar
import com.hysea.library.R
import com.hysea.library.interfaces.IBaseView
import com.hysea.library.manager.AppManager
import java.time.LocalDate

/**
 * Activity基类
 * Created by hysea on 2018/6/27.
 */
abstract class BaseActivity : AppCompatActivity(), IBaseView {
    private var immersionBar: ImmersionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeLayoutSetup(savedInstanceState)
        setContentView(getLayoutId())
        initSetup(savedInstanceState)
    }


    /**
     * 在布局之间设置
     */
    open fun beforeLayoutSetup(savedInstanceState: Bundle?) {
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


    override fun initEvent() {

    }

    override fun onDestroy() {
        AppManager.removeActivity(this)
        immersionBar?.destroy() // 防止内存泄漏
        super.onDestroy()
    }
}
