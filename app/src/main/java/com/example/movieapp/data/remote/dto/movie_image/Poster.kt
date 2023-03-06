package com.example.movieapp.data.remote.dto.movie_image

data class Poster(
    val aspect_ratio: Double,
    val file_path: String,
    val height: Int,
    val iso_639_1: String?,
    val vote_average: Float,
    val vote_count: Int,
    val width: Int
)