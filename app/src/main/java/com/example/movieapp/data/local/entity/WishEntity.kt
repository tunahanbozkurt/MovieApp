package com.example.movieapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WishEntity(
    @PrimaryKey val id: Int,
    val genre: String,
    val original_title: String,
    val poster_path: String,
    val vote_average: Double,
    val media_type: String
)
