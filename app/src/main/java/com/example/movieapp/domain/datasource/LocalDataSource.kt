package com.example.movieapp.domain.datasource

import com.example.movieapp.data.local.entity.MovieEntity
import com.example.movieapp.data.local.entity.WishEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getMovieFlow(): Flow<MovieEntity>
    suspend fun getMovie(): MovieEntity?
    suspend fun insertMovie(movie: MovieEntity)
    suspend fun insertWish(model: WishEntity)
    fun getWishFlow(): Flow<List<WishEntity>>
}