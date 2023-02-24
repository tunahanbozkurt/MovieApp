package com.example.movieapp.domain

import com.example.movieapp.data.remote.dto.genre.MovieGenreList
import retrofit2.Response

interface RemoteMovieDS {

    suspend fun getAllMovieGenres(apiKey: String): Response<MovieGenreList>
}