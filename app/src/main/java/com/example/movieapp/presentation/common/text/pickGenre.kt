package com.example.movieapp.presentation.common.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.movieapp.R
import com.example.movieapp.domain.model.detail.MovieDetail
import com.example.movieapp.domain.model.popular.MovieItem
import com.example.movieapp.presentation.home.elements.rememberGenreList

@Composable
fun pickGenre(movie: MovieItem): String {
    val genreList = rememberGenreList().value
    val context = LocalContext.current
    return try {
        genreList.genres.find {
            it.id == movie.genre_ids[0]
        }?.name ?: context.getString(R.string.unknown)
    } catch (e: IndexOutOfBoundsException) {
        context.getString(R.string.unknown)
    }
}

@Composable
fun pickGenre(movie: MovieDetail): String {
    val genreList = rememberGenreList().value
    val context = LocalContext.current
    return try {
        genreList.genres.find {
            it.id == movie.genres[0].id
        }?.name ?: context.getString(R.string.unknown)
    } catch (e: IndexOutOfBoundsException) {
        context.getString(R.string.unknown)
    }
}