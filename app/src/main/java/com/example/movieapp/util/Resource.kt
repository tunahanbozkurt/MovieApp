package com.example.movieapp.util

sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val message: String) : Resource<T>()
}

suspend fun <T, K> Resource<T>.onSuccess(
    onSuccess: suspend (Resource.Success<T>) -> K,
): K? {
    if (this is Resource.Success) {
        return onSuccess(this)
    }
    return null
}

suspend fun <T, K> Resource<T>.onError(
    onError: suspend (Resource.Error<T>) -> K,
): K? {
    if (this is Resource.Error) {
        return onError(this)
    }
    return null
}

suspend fun <T, K> Resource<T>.onLoading(
    onLoading: suspend (Resource.Loading<T>) -> K,
): K? {
    if (this is Resource.Loading) {
        return onLoading(this)
    }
    return null
}
