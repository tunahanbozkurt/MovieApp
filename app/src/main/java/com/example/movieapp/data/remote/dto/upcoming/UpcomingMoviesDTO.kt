package com.example.movieapp.data.remote.dto.upcoming

data class UpcomingMoviesDTO(
    val dates: Dates,
    val page: Int,
    val results: List<UpcomingMovieResult>,
    val total_pages: Int,
    val total_results: Int
)