package com.example.movieapp.util

import androidx.annotation.StringRes

sealed class TaskResult {
    object Success: TaskResult()
    data class Error(@StringRes val errMsg: Int): TaskResult()
}
