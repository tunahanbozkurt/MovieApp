package com.example.movieapp.util

import androidx.compose.runtime.Composable
import com.example.movieapp.domain.model.PopularMovie

@Composable
fun pickGenre(genreList: GenreList, movie: PopularMovie): String {
    return genreList.genres.find {
        it.id == movie.genre_ids[0]
    }?.name ?: ""
}