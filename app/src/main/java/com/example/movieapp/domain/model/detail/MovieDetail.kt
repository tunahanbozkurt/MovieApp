package com.example.movieapp.domain.model.detail

import com.example.movieapp.data.local.entity.MovieEntity
import com.example.movieapp.data.local.entity.WishEntity
import com.example.movieapp.data.remote.dto.detail.Genre
import com.example.movieapp.data.remote.dto.seriesdetail.Season
import com.example.movieapp.util.extensions.toGenreIdList

data class MovieDetail(
    val id: Int = 0,
    val genres: List<Genre> = listOf(),
    val original_title: String = "",
    val poster_path: String = "",
    val release_date: String = "",
    val runtime: Int = 0,
    val vote_average: Double = 0.0,
    val overview: String = "",
    val number_of_episodes: Int = 0,
    val number_of_seasons: Int = 0,
    val seasons: List<Season> = listOf(),
    val backdrop: String? = null
) {

    fun toMovieEntity(): MovieEntity {
        return MovieEntity(
            primaryKey = 0,
            id = id,
            genre_ids = genres.toGenreIdList(),
            original_title = original_title,
            poster_path = poster_path,
            vote_average = vote_average,
            release_date = release_date
        )
    }

    fun toWishEntity(mediaType: String): WishEntity {
        return WishEntity(
            id = id,
            genre = if (genres.isNotEmpty()) genres[0].name else "Unknown",
            original_title = original_title,
            poster_path = poster_path,
            vote_average = vote_average,
            media_type = mediaType,
            backdrop = backdrop ?: poster_path
        )
    }
}