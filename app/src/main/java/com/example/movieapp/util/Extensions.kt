package com.example.movieapp.util

import android.content.Context
import android.util.Patterns
import android.widget.Toast

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun CharSequence?.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun List<Any>.hasError(): Boolean {
    return this.any {
        it is TaskResult.Error || it == true
    }
}

fun TaskResult.isValid(): Boolean {
    return this is TaskResult.Success
}

fun TaskResult.isNotValid(): Boolean {
    return this is TaskResult.Error
}