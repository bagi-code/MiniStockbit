package com.robby.ministockbit.utils

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.View
import android.view.ViewParent
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
import com.robby.ministockbit.R

fun TextView.changeColorText(changesPrice: Double, context: Context) {
    if (changesPrice < 0) {
        this.setTextColor(ContextCompat.getColor(context, R.color.red))
    } else if (changesPrice == 0.0) {
        this.setTextColor(ContextCompat.getColor(context, R.color.grey))
    } else if (changesPrice > 0) {
        this.setTextColor(ContextCompat.getColor(context, R.color.green))
    }
}

fun getTextInputLayout(view: View): TextInputLayout? {
    return getTextInputLayout(view.parent, 3)
}

private fun getTextInputLayout(view: ViewParent, maxDepth: Int): TextInputLayout? {
    if (view.parent is TextInputLayout) {
        return view.parent as TextInputLayout
    } else if (maxDepth > 0) {
        return getTextInputLayout(view.parent, maxDepth - 1)
    }
    return null
}

fun TextView.string(): String {
    return text.toString()
}

fun TextView.setHtml(message : String) {
    this.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(message, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(message)
    }
}