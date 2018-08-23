package com.hysea.library.base

import android.os.Bundle
import android.view.View

/**
 * Fragment的懒加载
 * Created by hysea on 2018/7/3.
 */
abstract class BaseLazyFragment : BaseFragment() {
    protected var mIsViewCreated = false
    protected var mIsVisibleToUser = false
    protected var mIsFirstVisible = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mIsViewCreated = true
        if (mIsVisibleToUser) {
            onLazyLoad()
            mIsFirstVisible = false
            fragmentResume()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        mIsVisibleToUser = isVisibleToUser
        if (!mIsViewCreated) return // 如果视图未创建完成就返回
        if (isVisibleToUser) {
            fragmentResume()
            if (mIsFirstVisible) {
                onLazyLoad()
                mIsFirstVisible = false
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
