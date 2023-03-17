package com.example.movieapp.domain.model.detail

import com.example.movieapp.data.remote.dto.tvseasondetail.Episode

data class TvSeasonDetail(
    val episodes: List<Episode>,
    val poster_path: String?
)