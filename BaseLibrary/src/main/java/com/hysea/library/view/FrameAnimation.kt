package com.hysea.library.view

import android.widget.ImageView
import java.lang.ref.SoftReference

/**
 * 防OOM,低内存帧动画实现,比Android原生帧动画性能更好
 * Created by hysea on 2018/8/28.
 */
class FrameAnimation(
        iv: ImageView, // 播放动画的控件
        frameRes: IntArray, // 播放的图片数组
        duration: Int, // 每帧动画的播放间隔(毫秒)
        isRepeat: Boolean) // 是否循环播放
{


    private var mIsRepeat: Boolean = isRepeat

    private var mAnimationListener: AnimationListener? = null

    private var mSrImageView: SoftReference<ImageView> = SoftReference(iv)

    private var mFrameResArray: IntArray = frameRes

    /**
     * 每帧动画的播放间隔
     */
    private var mDuration: Int = duration

    /**
     * 最后一帧
     */
    private var mLastFrame: Int = frameRes.size - 1

    /**
     * 是否暂停
     */
    private var isPause: Boolean = false

    /**
     * 当前帧
     */
    private var mCurrentFrame: Int = 0


    /**
     * 开始动画
     */
    fun startAnimation() {
        play(0)
    }

    /**
     * 继续动画
     */
    fun continueAnimation() {
        play(mCurrentFrame)
    }

    /**
     * 停止动画
     */
    fun stopAnimation() {
        this.isPause = true
    }

    /**
     * 播放第i帧动画
     */
    private fun play(i: Int) {
        val imageView = mSrImageView.get()
        imageView?.postDelayed(Runnable {
            if (isPause) {
                mCurrentFrame = i
                return@Runnable
            }
            if (0 == i) {
                mAnimationListener?.onAnimationStart()
            }
            imageView.setBackgroundResource(mFrameResArray[i])
            if (i == mLastFrame) {
                if (mIsRepeat) {
                    mAnimationListener?.onAnimationRepeat()
                    play(0)
                } else {
                    mAnimationListener?.onAnimationEnd()
                }
            } else {
                play(i + 1)
            }
        }, mDuration.toLong())
    }

    interface AnimationListener {

        /**
         * the start of the animation.
         */
        fun onAnimationStart()

        /**
         * the end of the animation. This callback is not invoked
         * for animations with repeat count set to INFINITE.
         */
        fun onAnimationEnd()

        /**
         * the repetition of the animation.
         */
        fun onAnimationRepeat()
    }

    /**
     *
     * Binds an animation listener to this animation.
     */
    fun setAnimationListener(listener: AnimationListener) {
        this.mAnimationListener = listener
    }
}
