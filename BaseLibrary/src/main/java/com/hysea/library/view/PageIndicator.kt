package com.hysea.library.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.res.use
import com.hysea.library.R
import com.hysea.library.utils.dp

/**
 * 赛事列表指示器
 * @author: hysea
 * @date: 2020/6/12
 */
class PageIndicator @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var totalCount: Int = 0
    private var curIndex: Int = 0

    private val unSelectedPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = unSelectedColor
        style = Paint.Style.STROKE
        strokeWidth = 0.5f.dp
    }

    private val selectedPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = selectedColor
        style = Paint.Style.FILL_AND_STROKE
        strokeWidth = 0.5f.dp  // 加上，否则会导致未选中与选中的大小不一致
    }

    @ColorInt
    private var selectedColor:Int = Color.parseColor("#FFC2C2C2")

    @ColorInt
    private var unSelectedColor:Int = Color.parseColor("#FF909090")

    /**
     * 半径大小
     */
    private var radius = 1.5f.dp

    /**
     * 左右间距
     */
    private var margin = 3f.dp + radius * 2

    /**
     * 上下间隙
     */
    private val spacing = 4f.dp + radius * 2

    init {
        context.obtainStyledAttributes(attrs, R.styleable.PageIndicator).use {
            radius = it.getDimension(R.styleable.PageIndicator_radius, 1.5f.dp)
            selectedColor = it.getColor(R.styleable.PageIndicator_selected_color, Color.parseColor("#FFC2C2C2"))
            unSelectedColor = it.getColor(R.styleable.PageIndicator_unselected_color, Color.parseColor("#FF909090"))
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (totalCount > 1 && curIndex < totalCount) {
            canvas?.apply {
                this.save()
                this.translate(measuredWidth / 2 - (2 * radius + margin * (totalCount - 1)) / 2, measuredHeight - spacing)

                for (index in 0 until totalCount) {
                    if (curIndex == index) {
                        this.drawCircle(radius + margin * index, radius, radius, selectedPaint)
                    } else {
                        this.drawCircle(radius + margin * index, radius, radius, unSelectedPaint)
                    }
                }

                this.restore()
            }
        }
    }

    fun update(curIndex: Int, totalCount: Int) {
        this.curIndex = curIndex
        this.totalCount = totalCount
        invalidate()
    }
}