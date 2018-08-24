package com.hysea.library.interfaces

import android.support.v7.widget.RecyclerView

/**
 * 列表接口
 * Created by hysea on 2018/8/21.
 */
interface IBaseList {
    /**
     * 设置LayoutManager
     */
    fun getLayoutManager(): RecyclerView.LayoutManager

    /**
     * 设置分割线
     */
    fun setItemDecoration()

    fun setAdapter()

    /**
     * 加载更多
     */
    fun onLoadMore()

    /**
     * 下拉刷新
     */
    fun onRefresh()

    /**
     * 设置空布局
     */
    fun setEmptyLayout()
}