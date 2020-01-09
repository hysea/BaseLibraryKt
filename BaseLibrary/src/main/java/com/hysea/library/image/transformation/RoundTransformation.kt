package com.hysea.library.image.transformation

import android.content.Context
import android.graphics.*
import androidx.annotation.NonNull
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.util.Util
import com.hysea.library.utils.dp2px
import java.security.MessageDigest

/**
 * 圆角变换
 * Created by hysea on 2018/8/27.
 */
class RoundTransformation(context: Context, var radius: Float) : BitmapTransformation() {
    private val ID: String = RoundTransformation::class.java.name

    init {
        // 将单位dp换算成单位px
        radius = dp2px(context, radius).toFloat()
    }

    override fun transform(@NonNull pool: BitmapPool, @NonNull toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val width = toTransform.width
        val height = toTransform.height
        val bitmap = pool.get(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = BitmapShader(toTransform, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val rectF = RectF(0f, 0f, width.toFloat(), height.toFloat())
        canvas.drawRoundRect(rectF, radius, radius, paint)
        return bitmap
    }

    override fun equals(other: Any?): Boolean {
        if (other is RoundTransformation) {
            return radius == other.radius
        }
        return false
    }

    override fun hashCode(): Int {
        return Util.hashCode(ID.hashCode(), Util.hashCode(radius))
    }

    override fun updateDiskCacheKey(@NonNull messageDigest: MessageDigest) {
        messageDigest.update((ID + radius).toByteArray(CHARSET))
    }
}