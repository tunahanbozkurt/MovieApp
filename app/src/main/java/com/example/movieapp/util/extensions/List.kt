package com.example.movieapp.util.extensions

import com.example.movieapp.data.remote.dto.detail.Genre
import com.example.movieapp.util.TaskResult

fun List<Any>.hasError(): Boolean {
    return this.any {
        it is TaskResult.Error || it == true
    }
}

fun List<Genre>.toGenreIdList(): List<Int> {
    val list = mutableListOf<Int>()
    this.forEach { genre ->
        list.add(genre.id)
    }
    return list
}