package com.example.movieapp.data.remote.dto.movieVideo

import com.example.movieapp.domain.model.video.Videos

data class MovieVideosDTO(
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