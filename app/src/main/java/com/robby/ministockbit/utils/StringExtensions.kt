package com.robby.ministockbit.utils

import java.text.DecimalFormat

fun String.setPrefix(): String {
    val value = this.toDouble()
    return if (value < 0) {
        "-$value"
    } else if (value > 0) {
        "+$value"
    } else {
        this
    }
}

fun String.formatDoubleWithPrefix(): String {
    var valueFormat = DecimalFormat("##,##").format(this.toDouble())
    return valueFormat.setPrefix()
}