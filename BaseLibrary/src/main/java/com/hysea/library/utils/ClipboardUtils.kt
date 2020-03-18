package com.hysea.library.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

/**
 * 剪切板相关
 * create by hysea on 2020/1/20
 */


/**
 * 复制文本到剪切板
 */
fun copyTextToClipboard(context: Context, text: CharSequence) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
    clipboardManager?.primaryClip = ClipData.newPlainText("text", text)
}

/**
 * 获取剪切板文本
 */
fun getClipboardText(context: Context): CharSequence? {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
    return clipboardManager?.primaryClip?.run {
        if (this.itemCount > 0) this.getItemAt(0).coerceToText(context) else null
    }
}
