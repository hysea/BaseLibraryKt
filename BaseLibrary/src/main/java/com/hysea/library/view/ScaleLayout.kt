package com.hysea.library.view

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.hysea.library.R


/**
 * 按照比例显示的RelativeLayout
 * 以android:layout_width的宽度为基准
 */
class ScaleLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr) {
    var mScale = -1f

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScaleLayout)
        mScale = typedArray.getFloat(R.styleable.ScaleLayout_scale, mScale)
        typedArray.recycle()
    }

    public override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (mScale != -1f) {
            super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec((MeasureSpec.getSize(widthMeasureSpec) * mScale).toInt(), MeasureSpec.getMode(widthMeasureSpec)))
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}