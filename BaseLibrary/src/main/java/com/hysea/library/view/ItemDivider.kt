package com.hysea.library.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemDivider private constructor(builder: Builder) : RecyclerView.ItemDecoration() {

    private var lineHeight: Int
    private var lineColor: Int
    private var isIncludeEdge: Boolean

    private val mPaint: Paint?

    init {
        this.lineHeight = builder.dividerHeight
        this.lineColor = builder.dividerColor
        this.isIncludeEdge = builder.includeEdge

        mPaint = Paint()
        if (lineHeight <= 0) {
            lineHeight = 2
        }
        mPaint.color = lineColor
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val layoutManager = parent.layoutManager
        if (layoutManager is LinearLayoutManager) {
            handleLinearLayoutManager(outRect, view, parent)
        }

        if (layoutManager is GridLayoutManager) {
            handleGridLayoutManager(outRect, view, parent)
        }
    }

    private fun handleLinearLayoutManager(outRect: Rect, view: View, parent: RecyclerView) {
        val position = parent.getChildAdapterPosition(view)
        val orientation = getOrientation(parent)

        if (orientation == LinearLayoutManager.VERTICAL) {
            if (position == 0 && isIncludeEdge) {
                outRect.top = lineHeight
            }

            if (position == parent.childCount - 1 && isIncludeEdge) {
                outRect.bottom = lineHeight
            } else {
                outRect.bottom = lineHeight
            }
        } else {
            if (position == 0 && isIncludeEdge) {
                outRect.left = lineHeight
            }

            if (position == parent.childCount - 1 && isIncludeEdge)
                outRect.right = lineHeight
            else {
                outRect.right = lineHeight
            }
        }
    }

    private fun handleGridLayoutManager(outRect: Rect, view: View, parent: RecyclerView) {
        val position = parent.getChildAdapterPosition(view)
        val spanCount = (parent.layoutManager as GridLayoutManager).spanCount

        val column = position % spanCount
        if (isIncludeEdge) {
            outRect.left = lineHeight - column * lineHeight / spanCount
            outRect.right = (column + 1) * lineHeight / spanCount
            if (position < spanCount) {
                outRect.top = lineHeight
            }
            outRect.bottom = lineHeight
        } else {
            outRect.left = column * lineHeight / spanCount
            outRect.right = lineHeight - (column + 1) * lineHeight / spanCount
            if (position < spanCount) {
                outRect.top = lineHeight
            }
            outRect.bottom = lineHeight
        }
    }

    fun getOrientation(parent: RecyclerView): Int {
        val layoutManager = parent.layoutManager
        return (layoutManager as? LinearLayoutManager)?.orientation ?: LinearLayoutManager.VERTICAL
    }


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        if (parent.layoutManager == null || parent.layoutManager is GridLayoutManager) {
            return
        }

        if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }


    /**
     * 列表为竖直方向
     */
    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft
        val right = parent.measuredWidth - parent.paddingRight
        val childSize = parent.childCount
        val count = if (isIncludeEdge) childSize else childSize - 1 // childSize - 1：不绘制最后一个Item的分割线
        for (i in 0 until count) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + layoutParams.bottomMargin
            val bottom = top + lineHeight
            if (mPaint != null) {
                canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
            }
        }
    }

    /**
     * 列表为水平方向
     */
    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop
        val bottom = parent.measuredHeight - parent.paddingBottom
        val childSize = parent.childCount
        for (i in 0 until childSize) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + layoutParams.rightMargin
            val right = left + lineHeight
            if (mPaint != null) {
                canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
            }
        }
    }

    class Builder {
        var dividerHeight: Int = 0
        var dividerColor: Int = Color.TRANSPARENT
        var includeEdge: Boolean = false

        fun setDividerHeight(dividerHeight: Int): Builder {
            this.dividerHeight = dividerHeight
            return this
        }

        fun setDividerColor(@ColorInt dividerColor: Int): Builder {
            this.dividerColor = dividerColor
            return this
        }

        fun setIncludeEdge(includeEdge: Boolean): Builder {
            this.includeEdge = includeEdge
            return this
        }

        fun build(): ItemDivider {
            return ItemDivider(this)
        }
    }
}
