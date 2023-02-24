package com.example.movieapp.data.remote.dataSource

import com.example.movieapp.data.remote.MovieAPI
import com.example.movieapp.data.remote.dto.genre.MovieGenreList
import com.example.movieapp.domain.RemoteMovieDS
import retrofit2.Response

class RemoteMovieDSImpl(
    private val api: MovieAPI
): RemoteMovieDS {

    override suspend fun getAllMovieGenres(apiKey: String): Response<MovieGenreList> {
        return api.getAllMovieGenres(apiKey)
    }
}