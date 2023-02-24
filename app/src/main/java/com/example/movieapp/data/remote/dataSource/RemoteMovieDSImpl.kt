package com.example.movieapp.data.remote.dataSource

import com.example.movieapp.data.remote.MovieAPI
import com.example.movieapp.data.remote.dto.genre.MovieGenreListDTO
import com.example.movieapp.data.remote.dto.popular.PopularMoviesDTO
import com.example.movieapp.domain.RemoteMovieDS
import retrofit2.Response

class RemoteMovieDSImpl(
    private val api: MovieAPI
): RemoteMovieDS {

    override suspend fun getAllMovieGenres(apiKey: String): Response<MovieGenreListDTO> {
        return api.getAllMovieGenres(apiKey)
    }

    override suspend fun getPopularMovies(page: Int, apiKey: String): Response<PopularMoviesDTO> {
        return api.getPopularMovies(page = page, apiKey = apiKey)
    }
}