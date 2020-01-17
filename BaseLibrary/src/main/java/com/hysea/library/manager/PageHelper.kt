package com.hysea.library.manager

/**
 * create by hysea on 2019/12/11
 */

class PageHelper<T>(val listener: OnPageListener<T>?) {
    var currentPage = FIRST_PAGE

    var maxPageSize = PAGE_SIZE

    fun nextPage() {
        currentPage++
    }

    fun resetPage() {
        currentPage = FIRST_PAGE
    }

    fun handleResult(result: List<T>?) {
        if (result == null || (currentPage == FIRST_PAGE && result.isEmpty())) {
            listener?.onEmptyData()
        } else if (currentPage != FIRST_PAGE && result.isEmpty()) {
            listener?.onLoadCompleted()
        } else {
            if (currentPage == FIRST_PAGE) {
                listener?.onFirstPageData(result)
            } else {
                listener?.onNextPageData(result)
            }
        }
    }

    interface OnPageListener<T> {
        fun onEmptyData()
        fun onLoadCompleted()
        fun onFirstPageData(data: List<T>)
        fun onNextPageData(data: List<T>)
    }

    companion object {
        const val FIRST_PAGE = 1
        const val PAGE_SIZE = 10
    }
}