package com.foundao.likevideo.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hysea.library.interfaces.ICommon

/**
 * Fragment的基类
 * Created by hysea on 2018/6/27.
 */
abstract class BaseFragment : Fragment(), ICommon {
    lateinit var mContext: Context
    lateinit var mActivity: Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        if (context is Activity) {
            // 自动转换
            mActivity = context
        }
    }

    /**
     * 记住不要在onCreateView()中直接使用控件
     * 因为kotlin-android-extensions在布局文件加载完成后，会生成一个缓存视图
     * 但是在onCreateView()方法中，xml布局尚未完全加载到缓存视图中
     * 解决方案：在onViewCreated中使用
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)
    }

    final override fun <T : Any> readGo(clazz: Class<T>) {
        readGo(clazz, null)
    }

    final override fun <T : Any> readGo(clazz: Class<T>, bundle: Bundle?) {
        val intent = Intent(mContext, clazz)
        bundle?.let {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    final override fun <T : Any> readGoThenFinish(clazz: Class<T>) {
        readGoThenFinish(clazz, null)
    }

    final override fun <T : Any> readGoThenFinish(clazz: Class<T>, bundle: Bundle?) {
        val intent = Intent(mContext, clazz)
        bundle?.let {
            intent.putExtras(it)
        }
        startActivity(intent)
        mActivity.finish()
    }

    final override fun <T : Any> readGoForResult(clazz: Class<T>, requestCode: Int) {
        readGoForResult(clazz, null, requestCode)
    }

    final override fun <T : Any> readGoForResult(clazz: Class<T>, bundle: Bundle?, requestCode: Int) {
        val intent = Intent(mContext, clazz)
        bundle?.let {
            intent.putExtras(it)
        }
        startActivityForResult(intent, requestCode)
    }
}