package com.example.movieapp.domain

import com.example.movieapp.data.remote.dto.genre.MovieGenreList
import com.example.movieapp.util.Resource

interface MovieRepository {

    suspend fun getAllMovieGenres(apiKey: String): Resource<MovieGenreList>
}