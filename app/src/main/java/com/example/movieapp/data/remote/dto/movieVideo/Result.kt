package com.example.movieapp.data.remote.dto.movieVideo

import com.example.movieapp.domain.model.video.SiteInfo

data class Result(
    val id: String,
    val iso_3166_1: String,
    val iso_639_1: String,
    val key: String,
    val name: String,
    val official: Boolean,
    val published_at: String,
    val site: String,
    val size: Int,
    val type: String
) {

    fun toSiteInfo(): SiteInfo {
        return SiteInfo(
            site = site,
            key = key
        )
    }
}