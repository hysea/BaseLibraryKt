package com.hysea.library.image.transformation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.support.annotation.NonNull
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.util.Util
import com.hysea.library.utils.BlurUtils
import java.security.MessageDigest


/**
 * 毛玻璃变换
 * Created by hysea on 2018/8/27.
 */
class BlurTransformation(val context: Context, var radius: Int = MAX_RADIUS, var sampling: Int = DEFAULT_SAMPLING) : BitmapTransformation() {

    private val ID = BlurTransformation::class.java.name

    companion object {
        private const val MAX_RADIUS = 25
        private const val DEFAULT_SAMPLING = 1
    }

    init {
        //模糊半径0～25
        radius = if (radius > MAX_RADIUS) MAX_RADIUS else radius
        //取样0～25
        sampling = if (sampling > MAX_RADIUS) MAX_RADIUS else sampling
    }

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val width = toTransform.width
        val height = toTransform.height
        val scaledWidth = width / sampling
        val scaledHeight = height / sampling

        var bitmap = pool.get(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.scale(1 / sampling.toFloat(), 1 / sampling.toFloat())
        val paint = Paint()
        paint.flags = Paint.FILTER_BITMAP_FLAG
        canvas.drawBitmap(toTransform, 0f, 0f, paint)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            bitmap = BlurUtils.rsBlur(context, bitmap, radius)
        } else {
            bitmap = BlurUtils.blur(bitmap, radius)
        }
        return bitmap
    }

    override fun equals(other: Any?): Boolean {
        if (other is BlurTransformation) {
            return radius == other.radius && sampling == other.sampling
        }
        return false
    }

    override fun hashCode(): Int {
        return Util.hashCode(ID.hashCode(), Util.hashCode(radius, Util.hashCode(sampling)))
    }

    override fun updateDiskCacheKey(@NonNull messageDigest: MessageDigest) {
        messageDigest.update((ID + radius * 10 + sampling).toByteArray(CHARSET))
    }
}