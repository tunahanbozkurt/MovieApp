package com.example.movieapp.data.remote.dto.popular

import com.example.movieapp.domain.model.popular.PopularMovies

data class PopularMoviesDTO(
    val page: Int,
    val results: List<PopularMovieDTO>,
    val total_pages: Int,
    val total_results: Int
) {

    fun toPopularMovies(): PopularMovies {
        return PopularMovies(
            page = page,
            results = results.map { it.toPopularMovie() },
            total_pages = total_pages,
            total_results = total_results
        )
    }
}