package com.example.movieapp.data.remote.dto.detail

import com.example.movieapp.domain.model.detail.MovieDetail

data class MovieDetailDTO(
    val adult: Boolean,
    val backdrop_path: String?,
    val belongs_to_collection: Collection?,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String?,
    val id: Int,
    val imdb_id: String?,
    val original_language: String,
    val original_title: String,
    val overview: String?,
    val popularity: Double,
    val poster_path: String?,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int?,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {
    fun toMovieDetail(): MovieDetail {
        return MovieDetail(
            id = id,
            genres = genres,
            original_title = title,
            poster_path = poster_path ?: "",
            release_date = release_date,
            runtime = runtime ?: 0,
            vote_average = vote_average,
            overview = overview ?: "",
            backdrop = backdrop_path ?: ""
        )
    }
}