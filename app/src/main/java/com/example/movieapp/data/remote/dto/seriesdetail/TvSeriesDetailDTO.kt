package com.example.movieapp.data.remote.dto.seriesdetail

import com.example.movieapp.domain.model.detail.MovieDetail

data class TvSeriesDetailDTO(
    val backdrop_path: String?,
    val created_by: List<CreatedBy>,
    val episode_run_time: List<Int>,
    val first_air_date: String,
    val genres: List<com.example.movieapp.data.remote.dto.detail.Genre>,
    val homepage: String,
    val id: Int,
    val in_production: Boolean,
    val languages: List<String>,
    val last_air_date: String,
    val last_episode_to_air: LastEpisodeToAir,
    val name: String,
    val networks: List<Network>,
    val next_episode_to_air: Any?,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val seasons: List<Season>,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val type: String,
    val vote_average: Double,
    val vote_count: Int
) {

    fun toMovieDetail(): MovieDetail {
        return MovieDetail(
            id = id,
            genres = genres,
            number_of_episodes = number_of_episodes,
            number_of_seasons = number_of_seasons,
            seasons = seasons,
            original_title = original_name,
            poster_path = poster_path ?: "",
            release_date = first_air_date,
            runtime = if (episode_run_time.isNotEmpty()) episode_run_time[0] else 40,
            vote_average = vote_average,
            overview = overview
        )
    }
}