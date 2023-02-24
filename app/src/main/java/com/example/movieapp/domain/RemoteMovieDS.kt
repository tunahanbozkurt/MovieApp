package com.example.movieapp.domain

import com.example.movieapp.data.remote.dto.genre.MovieGenreListDTO
import com.example.movieapp.data.remote.dto.popular.PopularMoviesDTO
import retrofit2.Response

interface RemoteMovieDS {

    suspend fun getAllMovieGenres(apiKey: String): Response<MovieGenreListDTO>
    suspend fun getPopularMovies(page: Int, apiKey: String): Response<PopularMoviesDTO>
}