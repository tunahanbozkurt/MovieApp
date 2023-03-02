package com.example.movieapp.data.remote.dto.multiSearch

import com.example.movieapp.domain.model.popular.MovieItem

data class MultiSearchResult(
    val adult: Boolean,
    val backdrop_path: String?,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val known_for: List<KnownFor>,
    val media_type: String,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val profile_path: String?,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {

    fun toMovieItem(): MovieItem {
        if (media_type == "movie") {
            return MovieItem(
                id = id,
                type = "Movie",
                genre_ids = genre_ids,
                original_title = original_title,
                poster_path = poster_path,
                vote_average = vote_average,
                release_date = release_date
            )
        } else {
            return MovieItem(
                id = id,
                type = "Series",
                genre_ids = genre_ids,
                original_title = original_name,
                poster_path = poster_path,
                vote_average = vote_average,
                release_date = first_air_date
            )
        }

    }
}