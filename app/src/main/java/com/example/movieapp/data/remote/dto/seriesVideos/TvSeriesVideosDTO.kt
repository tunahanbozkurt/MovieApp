package com.example.movieapp.data.remote.dto.seriesVideos

import com.example.movieapp.domain.model.video.Videos

data class TvSeriesVideosDTO(
    val id: Int,
    val results: List<Result>
) {

    fun toVideos(): Videos {
        return Videos(
            results = results.map {
                it.toSiteInfo()
            }
        )
    }
}