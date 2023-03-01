package com.example.movieapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey val primaryKey: Int,
    val id: Int,
    val genre_ids: List<Int>,
    val original_title: String,
    val poster_path: String,
    val vote_average: Double,
    val release_date: String
)
