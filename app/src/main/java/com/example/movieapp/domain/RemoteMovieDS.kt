package com.example.movieapp.domain

import com.example.movieapp.data.remote.dto.genre.MovieGenreListDTO
import com.example.movieapp.data.remote.dto.popular.PopularMoviesDTO
import com.example.movieapp.data.remote.dto.upcoming.UpcomingMoviesDTO
import retrofit2.Response

interface RemoteMovieDS {

    suspend fun getAllMovieGenres(apiKey: String): Response<MovieGenreListDTO>
    suspend fun getPopularMovies(page: Int, apiKey: String): Response<PopularMoviesDTO>
    suspend fun getUpcomingMovies(page: Int, apiKey: String): Response<UpcomingMoviesDTO>
}