package com.hysea.library.base

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hysea.library.interfaces.IList

/**
 * 列表基础Fragment
 * Created by hysea on 2018/8/21.
 */
class BaseListFragment : BaseTitleActivity(), IList {
    override fun getLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutManager():RecyclerView.LayoutManager =
            LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)


    override fun setItemDecoration() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAdapter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadMore() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRefresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setEmptyLayout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}