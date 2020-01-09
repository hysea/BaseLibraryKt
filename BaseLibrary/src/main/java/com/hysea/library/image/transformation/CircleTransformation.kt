package com.hysea.library.image.transformation

import android.graphics.*
import androidx.annotation.NonNull
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.util.Util
import java.security.MessageDigest


/**
 * 圆形变换
 * Created by hysea on 2018/8/27.
 */
class CircleTransformation : BitmapTransformation() {
    private val ID: String = CircleTransformation::class.java.name


    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val size = Math.min(toTransform.width, toTransform.height)
        val x = (toTransform.width - size) / 2
        val y = (toTransform.height - size) / 2

        val square = Bitmap.createBitmap(toTransform, x, y, size, size)
        val circle = pool.get(size, size, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(circle)
        val paint = Paint()
        paint.shader = BitmapShader(square, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.isAntiAlias = true
        val r = size / 2f
        canvas.drawCircle(r, r, r, paint)
        return circle
    }

    override fun equals(other: Any?): Boolean {
        if (other is CircleTransformation) {
            return this === other
        }
        return false
    }

    override fun hashCode(): Int {
        return Util.hashCode(ID.hashCode())
    }

    override fun updateDiskCacheKey(@NonNull messageDigest: MessageDigest) {
        messageDigest.update(ID.toByteArray())
    }
}