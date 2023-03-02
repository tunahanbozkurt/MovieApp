package com.example.movieapp.data.remote.dto.tvCredits

data class TvShowCreditsDTO(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)