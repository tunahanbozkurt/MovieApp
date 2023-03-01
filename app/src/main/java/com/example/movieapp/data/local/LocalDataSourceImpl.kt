package com.example.movieapp.data.local

import com.example.movieapp.data.local.entity.MovieEntity
import com.example.movieapp.domain.datasource.LocalDataSource
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    private val dao: MovieDao
) : LocalDataSource {

    override fun getMovieFlow(): Flow<MovieEntity> {
        return dao.getMovieFlow()
    }

    override suspend fun getMovie(): MovieEntity? {
        return dao.getMovie()
    }

    override suspend fun insertMovie(movie: MovieEntity) {
        return dao.insertMovie(movie)
    }
}