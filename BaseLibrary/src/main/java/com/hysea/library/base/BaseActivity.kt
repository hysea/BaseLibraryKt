package com.hysea.library.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.gyf.barlibrary.ImmersionBar
import com.hysea.library.R
import com.hysea.library.interfaces.ICommon
import com.hysea.library.manager.AppManager

/**
 * Activity基类
 * Created by hysea on 2018/6/27.
 */
abstract class BaseActivity : AppCompatActivity(), ICommon {
    protected lateinit var mContext: Context
    protected var immersionBar: ImmersionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        AppManager.addActivity(this)
        mContext = this
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

    final override fun <T : Any> readGo(clazz: Class<T>) {
        readGo(clazz, null)
    }

    final override fun <T : Any> readGo(clazz: Class<T>, bundle: Bundle?) {
        val intent = Intent(this, clazz)
        bundle?.let {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    final override fun <T : Any> readGoThenFinish(clazz: Class<T>) {
        readGoThenFinish(clazz, null)
    }

    final override fun <T : Any> readGoThenFinish(clazz: Class<T>, bundle: Bundle?) {
        val intent = Intent(this, clazz)
        bundle?.let {
            intent.putExtras(it)
        }
        startActivity(intent)
        finish()
    }

    final override fun <T : Any> readGoForResult(clazz: Class<T>, requestCode: Int) {
        readGoForResult(clazz, null, requestCode)
    }

    final override fun <T : Any> readGoForResult(clazz: Class<T>, bundle: Bundle?, requestCode: Int) {
        val intent = Intent(this, clazz)
        bundle?.let {
            intent.putExtras(it)
        }
        startActivityForResult(intent, requestCode)
    }

    override fun onDestroy() {
        AppManager.removeActivity(this)
        immersionBar?.destroy() // 防止内存泄漏
        super.onDestroy()
    }
}
