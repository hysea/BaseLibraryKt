package com.hysea.library.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hysea.library.interfaces.IBaseView

/**
 * Fragment的基类
 * Created by hysea on 2018/6/27.
 */
abstract class BaseFragment : Fragment(), IBaseView {
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
        initEvent()
    }

    override fun initEvent() {

    }
}