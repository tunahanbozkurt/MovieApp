package com.example.movieapp.presentation.home.elements.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.data.remote.dto.multiSearch.MultiSearchResult
import com.example.movieapp.domain.model.popular.MovieItem
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.screen.search_result.ActorsList

@Composable
fun MovieListVertical(
    itemList: List<MovieItem>,
    list: List<MultiSearchResult>,
    modifier: Modifier = Modifier,
    onItemClick: (Int, String) -> Unit
) {

    LazyColumn(
        modifier = modifier
    ) {

        item {
            if (list.filter { it.media_type == "person" }
                    .isNotEmpty() &&
                list.any { it.profile_path != null }
            ) {
                ActorsList(
                    list = list.filter { it.media_type == "person" }
                )
            }
        }

        item {
            VerticalSpacer(heightDp = 24)
        }

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