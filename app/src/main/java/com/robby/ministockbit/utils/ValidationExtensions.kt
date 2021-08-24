package com.robby.ministockbit.utils

import android.util.Patterns
import android.widget.EditText

fun EditText.isFieldCondition(errorMessage: String, condition: Boolean, isFocus: Boolean): Boolean {
    val textInputLayout = getTextInputLayout(this)
    return if (condition) {
        if (textInputLayout != null) {
            textInputLayout.error = errorMessage
            textInputLayout.isErrorEnabled = true
        }
        if (isFocus) requestFocus()
        true
    } else {
        if (textInputLayout != null) {
            textInputLayout.isErrorEnabled = false
        }
        false
    }
}

fun String.isEmailValid(): Boolean {
    return if (this.isEmpty()) {
        false
    } else {
        return Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }
}