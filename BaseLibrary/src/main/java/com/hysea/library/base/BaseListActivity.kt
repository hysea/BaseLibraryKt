package com.hysea.library.base

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.hysea.library.R
import com.hysea.library.interfaces.IBaseList
import com.hysea.library.utils.inflateLayout
import kotlinx.android.synthetic.main.activity_base_list.*

/**
 * 列表基础Activity
 * Created by hysea on 2018/8/21.
 */
abstract class BaseListActivity : BaseTitleActivity(), IBaseList {
    override fun getContentView(): Int = R.layout.activity_base_list

    override fun init(savedInstanceState: Bundle?) {
        mRvList.layoutManager = getLayoutManager()
        mRvList.addItemDecoration(getItemDecoration())
        setAdapter(mRvList)

        // 刷新
        mSrlRefresh.setOnRefreshListener {
            onRefresh()
        }

        // 加载
        mSrlRefresh.setEnableLoadMore(isEnableLoadMore())
        if (isEnableLoadMore()) {
            mSrlRefresh.setOnLoadMoreListener {
                onLoadMore()
            }
        }
        if (isAutoRefresh()) {
            mSrlRefresh?.autoRefresh()
        }
    }

    fun getRecyclerView(): androidx.recyclerview.widget.RecyclerView = mRvList

    override fun finishRefresh() {
        mSrlRefresh?.finishRefresh()
    }

    override fun finishLoadmore() {
        mSrlRefresh?.finishLoadMore()
    }

    override fun finishLoadMoreWithNoMoreData() {
        mSrlRefresh?.finishLoadMoreWithNoMoreData()
    }

    override fun setEmptyLayout(layoutId: Int) {
        inflateLayout(layoutId, mEmptyLayout, true)
    }
}