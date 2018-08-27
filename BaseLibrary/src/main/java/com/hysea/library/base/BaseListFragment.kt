package com.hysea.library.base

import android.os.Bundle
import com.hysea.library.R
import com.hysea.library.interfaces.IBaseList
import com.hysea.library.utils.inflateLayout
import kotlinx.android.synthetic.main.fragment_base_list.*

/**
 * 列表基础Fragment
 * Created by hysea on 2018/8/21.
 */
abstract class BaseListFragment : BaseFragment(), IBaseList {

    override fun getLayoutId(): Int = R.layout.fragment_base_list

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
        mContext.inflateLayout(layoutId, mEmptyLayout,true)
    }
}