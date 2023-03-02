package com.example.movieapp.presentation.home.elements.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.domain.model.popular.MovieItem
import com.example.movieapp.presentation.common.spacer.VerticalSpacer

@Composable
fun MovieListVertical(
    itemList: List<MovieItem>,
    modifier: Modifier = Modifier,
    onItemClick: (Int, String) -> Unit
) {

    LazyColumn(
        modifier = modifier
    ) {

        items(itemList) { item ->
            if (item.poster_path != null) {
                MoviesListItemHorizontal(
                    model = item,
                    type = item.type
                ) { id, type ->
                    onItemClick.invoke(id, type)
                }
            }

            VerticalSpacer(heightDp = 16)
        }
    }
}

@Preview
@Composable
fun PreviewMovieListVertical() {

}