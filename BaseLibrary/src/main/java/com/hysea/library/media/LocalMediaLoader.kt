package com.hysea.library.media

import android.annotation.SuppressLint
import android.content.Context
import android.provider.MediaStore
import com.hysea.library.utils.applySchedules
import io.reactivex.Observable


/**
 * 本地媒体库加载
 * create by hysea on 2019/10/10
 */
object LocalMediaLoader {

    @SuppressLint("CheckResult")
    fun loadMedia(context: Context, type: MediaType, loadComplete: (List<MediaItem>?) -> Unit) {
        Observable.create<List<MediaItem>?> {
            when (type) {
                MediaType.TYPE_VIDEO -> loadMediaVideo(context)
                MediaType.TYPE_PICTURE -> loadMediaPicture(context)
            }
        }.applySchedules().subscribe {
            loadComplete.invoke(it)
        }
    }

    /**
     * 加载本地视频
     */
    private fun loadMediaVideo(context: Context): List<MediaItem> {
        val resolver = context.contentResolver
        val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val sortOrder = MediaStore.Video.Media.DATE_ADDED + " DESC"

        val cursor = resolver.query(uri, null, null, null, sortOrder)
        val result = mutableListOf<MediaItem>()

        if (cursor != null && cursor.count > 0) {
            while (cursor.moveToNext()) {
                val path = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA))
                val duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.DURATION))
                val width = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns.WIDTH))
                val height = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns.HEIGHT))
                // 过滤掉时长为0的数据
                if (duration > 0) {
                    result.add(MediaItem(
                            path = path,
                            durationMs = duration,
                            mediaType = MediaType.TYPE_VIDEO,
                            width = width,
                            height = height))
                }
            }
            cursor.close()
        }

        return result
    }


    /**
     * 加载本地图片
     */
    private fun loadMediaPicture(context: Context): List<MediaItem> {
        val resolver = context.contentResolver
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val sortOrder = MediaStore.Video.Media.DATE_ADDED + " DESC"

        val cursor = resolver.query(uri, null, null, null, sortOrder)
        val result = mutableListOf<MediaItem>()

        if (cursor != null && cursor.count > 0) {
            while (cursor.moveToNext()) {
                val path = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA))
                val size = cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns.SIZE))
                val width = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns.WIDTH))
                val height = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns.HEIGHT))

                result.add(MediaItem(
                        path = path,
                        size = size,
                        mediaType = MediaType.TYPE_PICTURE,
                        width = width,
                        height = height))
            }
            cursor.close()
        }

        return result
    }
}
