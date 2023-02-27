package com.example.movieapp.domain.datasource

import com.example.movieapp.data.remote.dto.detail.MovieDetailDTO
import com.example.movieapp.data.remote.dto.genre.MovieGenreListDTO
import com.example.movieapp.data.remote.dto.popular.PopularMoviesDTO
import com.example.movieapp.data.remote.dto.recommended.RecommendedMoviesDTO
import com.example.movieapp.data.remote.dto.search.MovieSearchDTO
import com.example.movieapp.data.remote.dto.upcoming.UpcomingMoviesDTO
import retrofit2.Response

interface RemoteMovieDS {

    suspend fun getAllMovieGenres(apiKey: String): Response<MovieGenreListDTO>
    suspend fun getPopularMovies(page: Int, apiKey: String): Response<PopularMoviesDTO>
    suspend fun getUpcomingMovies(page: Int, apiKey: String): Response<UpcomingMoviesDTO>
    suspend fun searchMovie(query: String, page: Int, apiKey: String): Response<MovieSearchDTO>
    suspend fun getMovieDetail(id: Int, apiKey: String): Response<MovieDetailDTO>
    suspend fun getRecommendedMovies(
        id: Int,
        page: Int,
        apiKey: String
    ): Response<RecommendedMoviesDTO>
}