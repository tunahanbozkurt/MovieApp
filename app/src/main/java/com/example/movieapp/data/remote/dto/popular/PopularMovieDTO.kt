package com.example.movieapp.data.remote.dto.popular

import com.example.movieapp.domain.model.popular.MovieItem

data class PopularMovieDTO(
    val adult: Boolean,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {
    fun toPopularMovie(): MovieItem {
        return MovieItem(
            id = id,
            genre_ids = genre_ids,
            original_title = original_title,
            poster_path = poster_path,
            vote_average = vote_average,
            release_date = release_date,
        )
    }
}