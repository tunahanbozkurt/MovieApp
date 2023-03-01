package com.example.movieapp.data.remote.dto.credits

data class MovieCreditsDTO(
    val cast: List<CastDTO>,
    val crew: List<CrewDTO>,
    val id: Int
)