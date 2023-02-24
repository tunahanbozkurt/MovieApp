package com.example.movieapp.util

import com.example.movieapp.data.remote.dto.genre.Genre

data class GenreList(
    val genres: List<Genre> = listOf()
)