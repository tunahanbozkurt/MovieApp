package com.example.movieapp.data.remote

import com.example.movieapp.data.remote.dto.detail.MovieDetailDTO
import com.example.movieapp.data.remote.dto.genre.MovieGenreListDTO
import com.example.movieapp.data.remote.dto.popular.PopularMoviesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("genre/movie/list")
    suspend fun getAllMovieGenres(
        @Query("api_key") apiKey: String
    ): Response<MovieGenreListDTO>

    @GET("movie/{movie_id}")
    suspend fun getDetail(
        @Query("api_key") apiKey: String,
        @Path("movie_id") movieId: Int,
    ): Response<MovieDetailDTO>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<PopularMoviesDTO>
}