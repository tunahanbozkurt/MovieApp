package com.example.movieapp.domain

import com.example.movieapp.data.remote.dto.genre.MovieGenreListDTO
import com.example.movieapp.data.remote.dto.popular.PopularMoviesDTO
import com.example.movieapp.domain.model.PopularMovie
import com.example.movieapp.domain.model.UpcomingMovie
import com.example.movieapp.util.Resource

interface MovieRepository {

    suspend fun getAllMovieGenres(apiKey: String): Resource<MovieGenreListDTO>
    suspend fun getPopularMovies(page: Int, apiKey: String): Resource<List<PopularMovie>>
    suspend fun getPopularMoviesForPaging(page: Int, apiKey: String): Resource<PopularMoviesDTO>
    suspend fun getUpcomingMovies(page: Int, apiKey: String): Resource<List<UpcomingMovie>>
}