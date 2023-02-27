package com.example.movieapp.presentation.home.elements

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.data.remote.dto.genre.Genre
import com.example.movieapp.domain.model.popular.MovieItem

@Composable
fun MovieListVertical(
    itemList: List<MovieItem>,
    selectedGenre: Genre,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
    ) {

        items(itemList) { item ->
            MoviesListItemHorizontal(
                imgUrl = item.poster_path ?: "" /*TODO PLACEHOLDER*/,
                title = item.original_title,
                year = item.release_date,
                genre = selectedGenre.name,
                rate = item.vote_average
            )
        }
    }
}

@Preview
@Composable
fun PreviewMovieListVertical() {
}