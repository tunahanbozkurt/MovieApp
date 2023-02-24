package com.example.movieapp.data.remote.dto.popular

data class PopularMoviesDTO(
    val page: Int,
    val results: List<PopularMovieDTO>,
    val total_pages: Int,
    val total_results: Int
)