package com.example.movieapp.domain.model.video

data class Videos(
    val results: List<SiteInfo>
)

data class SiteInfo(
    val site: String,
    val key: String
)