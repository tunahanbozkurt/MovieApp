package com.example.movieapp.domain.repository

import com.example.movieapp.data.remote.dto.detail.MovieDetailDTO
import com.example.movieapp.data.remote.dto.genre.MovieGenreListDTO
import com.example.movieapp.data.remote.dto.search.MovieSearchDTO
import com.example.movieapp.domain.model.UpcomingMovie
import com.example.movieapp.domain.model.popular.PopularMovies
import com.example.movieapp.domain.model.recommended.RecommendedMovies
import com.example.movieapp.util.Resource

interface MovieRepository {

    suspend fun getAllMovieGenres(apiKey: String): Resource<MovieGenreListDTO>
    suspend fun getPopularMovies(page: Int, apiKey: String): Resource<PopularMovies>
    suspend fun getUpcomingMovies(page: Int, apiKey: String): Resource<List<UpcomingMovie>>
    suspend fun getMovieDetail(id: Int, apiKey: String): Resource<MovieDetailDTO>
    suspend fun getRecommendedMovies(
        id: Int,
        page: Int,
        apiKey: String
    ): Resource<RecommendedMovies>

    suspend fun getSearchedMovies(
        query: String,
        page: Int,
        apiKey: String
    ): Resource<MovieSearchDTO>

}