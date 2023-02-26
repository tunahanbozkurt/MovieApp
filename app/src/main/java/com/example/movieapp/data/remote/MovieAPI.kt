package com.example.movieapp.data.remote

import com.example.movieapp.data.remote.dto.detail.MovieDetailDTO
import com.example.movieapp.data.remote.dto.genre.MovieGenreListDTO
import com.example.movieapp.data.remote.dto.popular.PopularMoviesDTO
import com.example.movieapp.data.remote.dto.search.MovieSearchDTO
import com.example.movieapp.data.remote.dto.upcoming.UpcomingMoviesDTO
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
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): Response<MovieDetailDTO>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Response<PopularMoviesDTO>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieSearchDTO>

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Response<UpcomingMoviesDTO>
}