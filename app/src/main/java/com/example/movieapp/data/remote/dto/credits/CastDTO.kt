package com.example.movieapp.data.remote.dto.credits

import com.example.movieapp.domain.model.cast_crew.CastCrew

data class CastDTO(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int?,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?
) {

    fun toCastCrew(): CastCrew {
        return CastCrew(
            id = id,
            isCastMember = true,
            name = original_name,
            profile_path = profile_path,
            position = known_for_department
        )
    }
}