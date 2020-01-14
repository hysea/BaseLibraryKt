package com.hysea.library.media

import android.media.MediaPlayer
import com.hysea.library.utils.LogUtils
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * 音乐播放管理
 * create by hysea on 2019/7/25
 */
object MusicManager {
    private const val PROGRESS_UPDATE_INTERVAL = 800L
    private var mediaPlayer: MediaPlayer? = null
    private var executor: ScheduledExecutorService? = null
    private var progressUpdateTask: Runnable? = null
    private var currentMediaState: MediaState = MediaState.MEDIA_STATE_STOP
    private var musicProgressListener: ((progress: Int, totalProgress: Int) -> Unit)? = null
    private var musicStateListener: ((state: MediaState) -> Unit)? = null

    private var path: String? = null

    private fun initMediaPlayer() {
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setOnCompletionListener {
            currentMediaState = MediaState.MEDIA_STATE_STOP
            musicStateListener?.invoke(currentMediaState)
            musicProgressListener?.invoke(
                    mediaPlayer?.currentPosition ?: 0, mediaPlayer?.duration ?: 0)
            stopProgressUpdate()
        }
    }

    fun setMusicProgressListener(musicProgressListener: (progress: Int, totalProgress: Int) -> Unit) {
        this.musicProgressListener = musicProgressListener
    }

    fun setMusicStateListener(musicStateListener: (state: MediaState) -> Unit) {
        this.musicStateListener = musicStateListener
    }

    fun isPlaying(): Boolean = mediaPlayer?.isPlaying ?: false

    fun startPlay(path: String) {
        if (mediaPlayer == null) {
            initMediaPlayer()
        }
        mediaPlayer?.let {
            it.reset()
            it.setDataSource(path)
            it.prepareAsync()
            it.setOnPreparedListener {
                MusicManager.path = path
                it.start()
                startProgressUpdate()
                currentMediaState = MediaState.MEDIA_STATE_PLAY
                musicStateListener?.invoke(currentMediaState)
            }
        }
    }

    fun stopPlay() {
        mediaPlayer?.stop()
        stopProgressUpdate()
        currentMediaState = MediaState.MEDIA_STATE_STOP
        musicStateListener?.invoke(currentMediaState)
    }

    fun pausePlay() {
        if (isPlaying()) {
            mediaPlayer?.pause()
            currentMediaState = MediaState.MEDIA_STATE_PAUSE
            musicStateListener?.invoke(currentMediaState)
        }
    }

    fun resumePlay() {
        if (currentMediaState == MediaState.MEDIA_STATE_PAUSE) {
            mediaPlayer?.start()
            currentMediaState = MediaState.MEDIA_STATE_PLAY
            musicStateListener?.invoke(currentMediaState)
        }
    }

    fun release() {
        mediaPlayer?.let {
            stopProgressUpdate()
            it.stop()
            it.release()
            path = null
            musicProgressListener = null
            musicStateListener = null
            mediaPlayer = null

        }
    }

    fun getState() = currentMediaState

    fun getCurrentPlayPath() = path

    private fun startProgressUpdate() {
        if (executor == null) {
            executor = Executors.newSingleThreadScheduledExecutor()
        }
        if (progressUpdateTask == null) {
            progressUpdateTask = Runnable {
                LogUtils.d("Music play progress : ${mediaPlayer?.currentPosition}:${mediaPlayer?.duration}")
                musicProgressListener?.invoke(
                        mediaPlayer?.currentPosition ?: 0, mediaPlayer?.duration ?: 0)
            }
        }
        executor?.scheduleAtFixedRate(progressUpdateTask, 0, PROGRESS_UPDATE_INTERVAL, TimeUnit.MILLISECONDS)
    }

    private fun stopProgressUpdate() {
        executor?.shutdownNow()
        executor = null
        progressUpdateTask = null
    }
}

enum class MediaState {
    MEDIA_STATE_PLAY,
    MEDIA_STATE_PAUSE,
    MEDIA_STATE_STOP
}