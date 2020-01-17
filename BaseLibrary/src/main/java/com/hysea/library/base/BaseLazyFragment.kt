package com.hysea.library.base

import android.os.Bundle
import android.view.View

/**
 * Fragment的懒加载
 * Created by hysea on 2018/7/3.
 */
abstract class BaseLazyFragment : BaseFragment() {
    private var isViewCreated = false
    private var isVisibleToUser = false
    private var isFirstVisible = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
        if (isVisibleToUser) {
            onLazyLoad()
            isFirstVisible = false
            fragmentResume()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        if (!isViewCreated) return // 如果视图未创建完成就返回
        if (isVisibleToUser) {
            fragmentResume()
            if (isFirstVisible) {
                onLazyLoad()
                isFirstVisible = false
            }
        } else {
            fragmentPause()
        }
    }

    /**
     * 懒加载，只会执行一次
     */
    protected open fun onLazyLoad() {

    }

    /**
     * fragment已创建，并且用户可见，该方法调用
     */
    protected open fun fragmentResume() {

    }

    /**
     * fragment已创建，并且用户不可见，该方法调用
     */
    protected open fun fragmentPause() {

    }
}
