package com.example.movieapp.domain.datasource

import com.example.movieapp.data.local.entity.MovieEntity
import com.example.movieapp.data.local.entity.WishEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    /**
     * Fetches the movie entity from the local database.
     */
    suspend fun getMovie(): MovieEntity?

    /**
     * Inserts the movie entity to the local database.
     */
    suspend fun insertMovie(movie: MovieEntity)

    /**
     * Inserts the wish entity to the local database.
     */
    suspend fun insertWish(model: WishEntity)

    /**
     * Deletes the wish entity from the local database.
     */
    suspend fun deleteWish(entity: WishEntity)

    /**
     * Provides the movie entities as a flow from the local database.
     */
    fun getMovieFlow(): Flow<MovieEntity>

    /**
     * Provides the wish entities as a flow from the local database.
     */
    fun getWishFlow(): Flow<List<WishEntity>>
}