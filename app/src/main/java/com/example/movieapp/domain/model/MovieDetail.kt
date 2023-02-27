package com.example.movieapp.domain.model

import com.example.movieapp.data.remote.dto.detail.Genre

data class MovieDetail(
    val id: Int,
    val genres: List<Genre>,
    val original_title: String,
    val poster_path: String?,
    val release_date: String,
    val runtime: Int?,
    val vote_average: Double,
)
