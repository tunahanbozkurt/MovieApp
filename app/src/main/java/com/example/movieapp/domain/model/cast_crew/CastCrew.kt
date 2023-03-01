package com.example.movieapp.domain.model.cast_crew

data class CastCrew(
    val id: Int,
    val isCastMember: Boolean,
    val name: String,
    val profile_path: String?,
    val position: String
)
