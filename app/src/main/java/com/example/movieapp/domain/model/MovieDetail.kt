package com.example.movieapp.domain.model

import com.example.movieapp.data.remote.dto.detail.Genre

data class MovieDetail(
    val id: Int = 0,
    val genres: List<Genre> = listOf(),
    val original_title: String = "",
    val poster_path: String = "",
    val release_date: String = "",
    val runtime: Int = 0,
    val vote_average: Double = 0.0,
    val overview: String = "",
)
