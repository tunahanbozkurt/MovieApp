package com.example.movieapp.data.local.data_source

import androidx.room.*
import com.example.movieapp.data.local.entity.MovieEntity
import com.example.movieapp.data.local.entity.WishEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentity")
    fun getMovieFlow(): Flow<MovieEntity>

    @Query("SELECT * FROM movieentity")
    suspend fun getMovie(): MovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM wishentity")
    fun getWishFlow(): Flow<List<WishEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWish(entity: WishEntity)

    @Delete
    suspend fun deleteWish(entity: WishEntity)
}