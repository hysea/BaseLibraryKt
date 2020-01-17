package com.hysea.library.base

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.hysea.library.R
import com.hysea.library.constant.Constants
import com.hysea.library.utils.dp2px
import com.hysea.library.utils.screenWidth

/**
 * create by hysea on 2019/10/22
 */
abstract class BaseDialogFragment : DialogFragment() {
    /** 背景透明度 */
    var dimAmount = 0.5f
    /** 左右边距 */
    var margin = 0f
    /** dialog高度 */
    var height = ViewGroup.LayoutParams.WRAP_CONTENT.toFloat()
    /** 进入退出动画 */
    var animStyle = Constants.NONE
    /** 点击外部取消 */
    var isCancelOnTouchOutSide = true
    /** 是否底部显示 */
    var showBottomEnable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.AppTheme_Dialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutId(), container, false)
        initView(view)
        return view
    }

    override fun onStart() {
        super.onStart()
        initParams()
        setupParams()
    }

    private fun setupParams() {
        dialog?.window?.let {
            val params = it.attributes
            params.dimAmount = dimAmount
            if (showBottomEnable) {
                params.gravity = Gravity.BOTTOM
            }
            params.width = context!!.screenWidth - 2 * context!!.dp2px(margin)
            if (height > 0) {
                params.height = context!!.dp2px(height)
            } else {
                params.height = height.toInt()
            }
            if (animStyle != Constants.NONE) {
                it.setWindowAnimations(animStyle)
            }
            it.attributes = params
        }
        dialog?.setCanceledOnTouchOutside(isCancelOnTouchOutSide)
    }


    open fun show(manager: FragmentManager) {
        super.show(manager, System.currentTimeMillis().toString())
    }

    abstract fun getLayoutId(): Int

    abstract fun initView(contentView: View)

    protected open fun initParams() {}

}