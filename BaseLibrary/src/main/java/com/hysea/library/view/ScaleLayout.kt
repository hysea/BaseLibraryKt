package com.hysea.library.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.res.use
import com.hysea.library.R


/**
 * 可以按照比例设置的FrameLayout
 * 以android:layout_width的宽度为基准
 */
class ScaleLayout(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private var scaleRatio = DEFAULT_SCALE_RATIO

    init {
        context.obtainStyledAttributes(attrs, R.styleable.ScaleLayout).use {
            scaleRatio = it.getFloat(R.styleable.ScaleLayout_scale_ratio, scaleRatio)
        }
    }

    public override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (scaleRatio != -1f) {
            super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec((MeasureSpec.getSize(widthMeasureSpec) * scaleRatio).toInt(), MeasureSpec.getMode(widthMeasureSpec)))
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    companion object {
        const val DEFAULT_SCALE_RATIO = -1f
    }
}