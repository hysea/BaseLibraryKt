package com.hysea.library.media

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * create by hysea on 2019/10/9
 */
@Parcelize
data class MediaItem(
        var path: String? = null,
        var durationMs: Long = 0,
        var size: Long = 0,
        var isSelected: Boolean = false,
        val mediaType: MediaType? = null,
        val width: Int = 0,
        val height: Int = 0
) : Parcelable

enum class MediaType {
    TYPE_VIDEO,
    TYPE_PICTURE
}