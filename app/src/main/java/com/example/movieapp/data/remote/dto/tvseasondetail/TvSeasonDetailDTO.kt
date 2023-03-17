package com.example.movieapp.data.remote.dto.tvseasondetail

import com.example.movieapp.domain.model.detail.TvSeasonDetail

data class TvSeasonDetailDTO(
    val _id: String,
    val air_date: String,
    val episodes: List<Episode>,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String?,
    val season_number: Int
) {

    fun toTvSeasonDetail(): TvSeasonDetail {
        return TvSeasonDetail(
            episodes = episodes,
            poster_path = poster_path
        )
    }
}