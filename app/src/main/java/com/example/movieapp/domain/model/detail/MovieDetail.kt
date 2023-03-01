package com.example.movieapp.domain.model.detail

import com.example.movieapp.data.local.entity.MovieEntity
import com.example.movieapp.data.remote.dto.detail.Genre
import com.example.movieapp.util.toGenreIdList

data class MovieDetail(
    val id: Int = 0,
    val genres: List<Genre> = listOf(),
    val original_title: String = "",
    val poster_path: String = "",
    val release_date: String = "",
    val runtime: Int = 0,
    val vote_average: Double = 0.0,
    val overview: String = "",
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
}
