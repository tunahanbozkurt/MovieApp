package com.example.movieapp.data.remote.dto.recommended

import com.example.movieapp.domain.model.recommended.RecommendedMovies

data class RecommendedMoviesDTO(
    val page: Int,
    val results: List<RecommendedMoviesResultDTO>,
    val total_pages: Int,
    val total_results: Int
) {

    fun toRecommendedMovies(): RecommendedMovies {
        return RecommendedMovies(
            page = page,
            results = results.map { it.toMovieItem() },
            total_pages = total_pages,
            total_results = total_results
        )
    }
}