package com.example.movieapp.presentation.common.text

import androidx.compose.runtime.Composable
import com.example.movieapp.domain.model.popular.MovieItem
import com.example.movieapp.presentation.home.elements.rememberGenreList

@Composable
fun pickGenre(movie: MovieItem): String {
    val genreList = rememberGenreList().value
    return try {
        genreList.genres.find {
            it.id == movie.genre_ids[0]
        }?.name ?: "Unknown"
    } catch (e: IndexOutOfBoundsException) {
        "Unknown"
    }
}