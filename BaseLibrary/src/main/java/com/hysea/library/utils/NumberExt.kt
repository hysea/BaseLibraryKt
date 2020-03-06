package com.hysea.library.utils

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * create by hysea on 2020/3/3
 */

/**
 * 保留小数位
 * @param bit 保留小数点后的位数
 */
fun Double.keepDecimal(bit: Int, roundingMode: RoundingMode = RoundingMode.HALF_UP): Double {
    return BigDecimal(this).setScale(bit, roundingMode).toDouble()
}