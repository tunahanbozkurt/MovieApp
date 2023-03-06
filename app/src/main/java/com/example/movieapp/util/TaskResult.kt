package com.example.movieapp.util

import androidx.annotation.StringRes

sealed class TaskResult {
    object Success : TaskResult()
    data class Error(@StringRes val errMsg: Int) : TaskResult()
}

fun TaskResult.onSuccess(): Boolean {
    return this is TaskResult.Success
}

fun TaskResult.onSuccess(execute: () -> Unit) {
    return execute.invoke()
}

fun TaskResult.onError(): Boolean {
    return this is TaskResult.Error
}

fun TaskResult.onError(execute: () -> Unit) {
    return execute.invoke()
}
