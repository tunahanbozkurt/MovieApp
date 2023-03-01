package com.example.movieapp.domain.datasource

import com.example.movieapp.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getMovieFlow(): Flow<MovieEntity>
    suspend fun getMovie(): MovieEntity?
    suspend fun insertMovie(movie: MovieEntity)
}