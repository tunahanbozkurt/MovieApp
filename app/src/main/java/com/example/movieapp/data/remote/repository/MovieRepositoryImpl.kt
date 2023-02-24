package com.example.movieapp.data.remote.repository

import com.example.movieapp.data.remote.dto.genre.MovieGenreList
import com.example.movieapp.domain.MovieRepository
import com.example.movieapp.domain.RemoteMovieDS
import com.example.movieapp.util.Resource
import com.example.movieapp.util.safeRequest
import kotlinx.coroutines.CoroutineDispatcher

class MovieRepositoryImpl(
    private val remoteDataSource: RemoteMovieDS,
    private val ioDispatcher: CoroutineDispatcher,
) : MovieRepository {

    override suspend fun getAllMovieGenres(apiKey: String): Resource<MovieGenreList> {
        return safeRequest(ioDispatcher) {
            remoteDataSource.getAllMovieGenres(apiKey)
        }
    }
}