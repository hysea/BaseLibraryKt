package com.hysea.library.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.hysea.library.image.transformation.CircleTransformation
import com.hysea.library.image.transformation.RoundTransformation

/**
 * 图片加载器
 * Created by hysea on 2018/8/27.
 */

/**
 * 加载一般图片
 */
fun ImageView.loadImage(url: Any?) {
    get(url).into(this)
}

/**
 * 加载圆角图片
 */
fun ImageView.loadRoundImage(url: Any?, radius: Float = 3.0f) {
    get(url).apply(getRoundRequest(context, radius))
            .into(this)
}

/**
 * 加载圆角图片，带占位图
 */
fun ImageView.loadRoundImage(url: Any?, radius: Float = 3.0f, placeHolder: Int, error: Int = placeHolder) {
    get(url).apply(getRoundRequest(context, radius).placeholder(placeHolder).error(error))
            .into(this)
}

/**
 * 加载圆形图片
 */
fun ImageView.loadCircleImage(url: Any?) {
    get(url).apply(getCircleRequest())
            .into(this)
}

/**
 * 加载圆形图片,带占位图
 */
fun ImageView.loadCircleImage(url: Any?, placeHolder: Int, error: Int = placeHolder) {
    get(url).apply(getCircleRequest().placeholder(placeHolder).error(error))
            .into(this)
}

private fun getCircleRequest() = RequestOptions.bitmapTransform(CircleTransformation())

private fun getRoundRequest(context: Context, radius: Float): RequestOptions {
    return RequestOptions()
            /**
             * 解决Glide与scaleType冲突，不能正常加载圆角
             */
            .transform(MultiTransformation(CenterCrop(), RoundTransformation(context, radius)))
}

private fun ImageView.get(url: Any?): RequestBuilder<Drawable> = Glide.with(context).load(url)