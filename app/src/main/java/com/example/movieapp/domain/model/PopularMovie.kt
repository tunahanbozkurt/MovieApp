package com.example.movieapp.domain.model

data class PopularMovie(
    val id: Int = 0,
    val genre_ids: List<Int> = listOf(),
    val original_title: String = "",
    val poster_path: String? = null,
    val vote_average: Double = 0.0,
)

data class PopularMovieList(
    val list: List<PopularMovie> = listOf()
)
