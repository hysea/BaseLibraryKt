package com.hysea.library.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.RelativeLayout
import com.hysea.library.R
import com.hysea.library.utils.hide
import com.hysea.library.utils.show
import com.hysea.library.utils.sp2px
import kotlinx.android.synthetic.main.layout_title_bar.view.*


/**
 * 通用标题栏
 */
class TitleBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : RelativeLayout(context, attrs), View.OnClickListener {
    /**
     * 右边按钮的文字颜色
     */
    private var leftButtonTextColor: Int = 0
    /**
     * 右边保存按钮的文字大小
     */
    private var leftButtonTextSize: Int = 0
    /**
     * 标题栏的背景颜色
     */
    private var titleBackgroundColor: Int = 0
    /**
     * 标题栏的显示的标题文字
     */
    private var titleText: String? = null
    /**
     * 标题栏的显示的标题文字颜色
     */
    private var titleTextColor: Int = 0
    /**
     * 标题栏的显示的标题文字大小
     */
    private var titleTextSize: Int = 0
    /**
     * 右边保存按钮的资源图片
     */
    private var rightButtonImageId: Int = 0
    /**
     * 右边保存按钮的文字
     */
    private var rightButtonText: String? = null
    /**
     * 右边按钮的文字颜色
     */
    private var rightButtonTextColor: Int = 0
    /**
     * 右边保存按钮的文字大小
     */
    private var rightButtonTextSize: Int = 0

    /**
     * 返回按钮上显示的文字
     */
    private var leftButtonText: String? = null
    /**
     * 返回按钮的资源图片
     */
    private var leftButtonImageId: Int = 0

    /**
     * 是否显示分割线
     */
    private var isShowLine: Boolean = false

    private var mContentView: View = View.inflate(context, R.layout.layout_title_bar, this)

    private var mTitleBarOnClickListener: (View) -> Unit = {}

    init {
        initAttrs(context, attrs)
    }


    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar)
        leftButtonImageId = typedArray.getResourceId(R.styleable.TitleBar_left_button_image, 0)
        leftButtonText = typedArray.getString(R.styleable.TitleBar_left_button_text)
        leftButtonTextColor = typedArray.getColor(R.styleable.TitleBar_left_button_textColor, Color.GRAY)
        leftButtonTextSize = typedArray.getDimensionPixelSize(R.styleable.TitleBar_left_button_textSize, 14)

        titleBackgroundColor = typedArray.getColor(R.styleable.TitleBar_title_background, Color.WHITE)
        titleText = typedArray.getString(R.styleable.TitleBar_title_text)
        titleTextColor = typedArray.getColor(R.styleable.TitleBar_title_textColor, Color.GRAY)
        titleTextSize = typedArray.getDimensionPixelSize(R.styleable.TitleBar_title_textSize, sp2px(context, 15f))

        rightButtonImageId = typedArray.getResourceId(R.styleable.TitleBar_right_button_image, 0)
        rightButtonText = typedArray.getString(R.styleable.TitleBar_right_button_text)
        rightButtonTextColor = typedArray.getColor(R.styleable.TitleBar_right_button_textColor, Color.GRAY)
        rightButtonTextSize = typedArray.getDimensionPixelSize(R.styleable.TitleBar_right_button_textSize, sp2px(context, 15f))

        isShowLine = typedArray.getBoolean(R.styleable.TitleBar_show_line, true)
        mContentView.line.visibility = if (isShowLine) View.VISIBLE else View.GONE

        setTitleBarBackground(titleBackgroundColor)
        setTitle(titleText)
        setTitleTextSize(titleTextSize)
        setTitleTextColor(titleTextColor)

        setLeftIcon(leftButtonImageId)
        setTvLeft(leftButtonText)
        setTvLeftTextSize(leftButtonTextSize)
        setTvLeftTextColor(leftButtonTextColor)

        setRightIcon(rightButtonImageId)
        setTvRight(rightButtonText)
        setTvRightTextColor(rightButtonTextColor)
        setTvRightTextSize(rightButtonTextSize)

        typedArray.recycle()
    }

    fun setTitle(title: String?) {
        if (title.isNullOrEmpty()) {
            mContentView.mTvTitle.hide()
        } else {
            mContentView.mTvTitle.text = title
            mContentView.mTvTitle.show()
        }
    }

    fun setTitleTextSize(textSize: Int) {
        mContentView.mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
    }

    fun setTitleTextColor(textColor: Int) {
        mContentView.mTvTitle.setTextColor(textColor)
    }

    fun setTvLeft(text: String?) {
        if (text.isNullOrEmpty()) {
            mContentView.mTvLeft.hide()
        } else {
            mContentView.mTvLeft.show()
            mContentView.mTvLeft.text = text
        }
    }

    /**
     * @param textSize 以px为单位
     */
    fun setTvLeftTextSize(textSize: Int) {
        mContentView.mTvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
    }

    fun setTvLeftTextColor(textColor: Int) {
        mContentView.mTvLeft.setTextColor(textColor)
    }

    fun setTvRight(text: String?) {
        if (text.isNullOrEmpty()) {
            mContentView.mTvRight.visibility = View.GONE
        } else {
            mContentView.mTvRight.visibility = View.VISIBLE
            mContentView.mTvRight.text = text
        }
    }


    fun setTvRightTextSize(textSize: Int) {
        mContentView.mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
    }


    fun setTvRightTextColor(textColor: Int) {
        mContentView.mTvRight.setTextColor(textColor)
    }


    fun setLeftIcon(resId: Int) {
        if (resId == 0) {
            mContentView.mIvLeft.hide()
        } else {
            mContentView.mIvLeft.show()
            mContentView.mIvLeft.setImageResource(resId)
        }
    }

    fun setRightIcon(resId: Int) {
        if (resId == 0) {
            mContentView.mIvRight.hide()
        } else {
            mContentView.mIvRight.show()
            mContentView.mIvRight.setImageResource(resId)
        }
    }

    fun setAction(listener: (View) -> Unit) {
        mContentView.mIvLeft.setOnClickListener(this)
        mContentView.mIvRight.setOnClickListener(this)
        mContentView.mTvLeft.setOnClickListener(this)
        mContentView.mTvRight.setOnClickListener(this)
        mTitleBarOnClickListener = listener
    }


    fun setLineIsVisible(visibility: Int) {
        mContentView.line.visibility = visibility
    }


    fun setShowRightButton(showRightButton: Boolean) {
        if (showRightButton) {
            mContentView.mTvRight.show()
            mContentView.mIvRight.show()
        } else {
            mContentView.mTvRight.hide()
            mContentView.mIvRight.hide()
        }
    }

    fun setShowLeftButton(showLeftButton: Boolean) {
        if (showLeftButton) {
            mContentView.mTvLeft.show()
            mContentView.mIvLeft.show()
        } else {
            mContentView.mTvLeft.hide()
            mContentView.mIvLeft.hide()
        }
    }


    fun setTitleBarBackground(resId: Int) {
        mContentView.mRelativeLayout.setBackgroundColor(resId)
    }

    interface TitleBarOnClickListener {
        fun performAction(view: View)
    }

    override fun onClick(v: View) {
        mTitleBarOnClickListener(v)
    }
}
