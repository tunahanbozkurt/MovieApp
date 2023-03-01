package com.example.movieapp.presentation.home.elements.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.data.remote.dto.genre.Genre
import com.example.movieapp.domain.model.popular.MovieItem
import com.example.movieapp.presentation.common.spacer.VerticalSpacer

@Composable
fun MovieListVertical(
    itemList: List<MovieItem>,
    selectedGenre: Genre,
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit
) {

    LazyColumn(
        modifier = modifier
    ) {

        items(itemList) { item ->
            if (item.poster_path != null) {
                MoviesListItemHorizontal(
                    model = item
                ) { id ->
                    onItemClick.invoke(id)
                }
            }

            VerticalSpacer(heightDp = 16)
        }
    }
}

@Preview
@Composable
fun PreviewMovieListVertical() {
    MovieListVertical(
        itemList = listOf(
            MovieItem(
                id = 0,
                genre_ids = listOf(),
                original_title = "Spider-Man",
                poster_path = "",
                vote_average = 5.6,
                release_date = "2022",
            )
        ),
        selectedGenre = Genre(0, "All")
    ) {

    }
}