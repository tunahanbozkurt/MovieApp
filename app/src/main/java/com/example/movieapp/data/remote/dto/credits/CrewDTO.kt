package com.example.movieapp.data.remote.dto.credits

import com.example.movieapp.domain.model.cast_crew.CastCrew

data class CrewDTO(
    val adult: Boolean,
    val credit_id: String,
    val department: String,
    val gender: Int?,
    val id: Int,
    val job: String,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?
) {

    fun toCastCrew(): CastCrew {
        return CastCrew(
            id = id,
            isCastMember = false,
            name = original_name,
            profile_path = profile_path,
            position = known_for_department
        )
    }
}