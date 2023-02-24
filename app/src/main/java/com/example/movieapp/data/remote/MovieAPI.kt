package com.example.movieapp.data.remote

import com.example.movieapp.BuildConfig
import com.example.movieapp.data.remote.dto.genre.MovieGenreList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("genre/movie/list")
    suspend fun getAllMovieGenres(
        @Query("api_key") apiKey: String
    ): Response<MovieGenreList>
}