package com.hysea.library.interfaces

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * 列表接口
 * Created by hysea on 2018/8/21.
 */
interface IBaseList {
    /**
     * 设置LayoutManager
     */
    fun getLayoutManager(): androidx.recyclerview.widget.RecyclerView.LayoutManager

    /**
     * 设置分割线
     */
    fun getItemDecoration(): androidx.recyclerview.widget.RecyclerView.ItemDecoration

    fun setAdapter(recyclerView: androidx.recyclerview.widget.RecyclerView)

    /**
     * 加载更多
     */
    fun onLoadMore()

    /**
     * 下拉刷新
     */
    fun onRefresh()

    fun isEnableLoadMore(): Boolean

    fun isAutoRefresh(): Boolean

    /**
     * 刷新结束
     */
    fun finishRefresh()

    /**
     * 加载结束
     */
    fun finishLoadmore()

    /**
     * 加载结束，没数据
     */
    fun finishLoadMoreWithNoMoreData()

    /**
     * 设置空布局
     */
    fun setEmptyLayout(@LayoutRes layoutId: Int)
}