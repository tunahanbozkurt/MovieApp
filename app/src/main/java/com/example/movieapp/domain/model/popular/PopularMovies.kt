package com.example.movieapp.domain.model.popular

data class PopularMovies(
    val page: Int = 1,
    val results: List<MovieItem> = listOf(),
    val total_pages: Int = 1,
    val total_results: Int = 1
)