package com.example.movieapp.domain.model.recommended

import com.example.movieapp.domain.model.popular.MovieItem

data class RecommendedMovies(
    val page: Int = 1,
    val results: List<MovieItem> = listOf(),
    val total_pages: Int = 1,
    val total_results: Int = 1
)
