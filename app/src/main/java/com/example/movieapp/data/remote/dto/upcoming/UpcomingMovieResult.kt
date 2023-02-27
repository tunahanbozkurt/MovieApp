package com.example.movieapp.data.remote.dto.upcoming

import com.example.movieapp.domain.model.UpcomingMovie

data class UpcomingMovieResult(
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
    fun toUpcomingMovie(): UpcomingMovie {
        return UpcomingMovie(
            id = id,
            imgUrl = backdrop_path ?: "",
            title = original_title,
            releaseDate = release_date ?: ""
        )
    }
}