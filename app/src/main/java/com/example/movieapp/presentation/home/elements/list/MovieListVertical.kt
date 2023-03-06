package com.example.movieapp.presentation.home.elements.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.movieapp.data.remote.dto.multiSearch.MultiSearchResult
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.screen.detail.ItemType
import com.example.movieapp.presentation.home.screen.search_result.ActorsList

@Composable
fun MovieListVertical(
    itemList: LazyPagingItems<MultiSearchResult>,
    list: List<MultiSearchResult>,
    modifier: Modifier = Modifier,
    onItemClick: (Int, String) -> Unit
) {

    LazyColumn(
        modifier = modifier
    ) {

        item {
            if (list.filter { it.media_type == ItemType.PERSON.type }
                    .isNotEmpty() &&
                list.any { it.profile_path != null }
            ) {
                ActorsList(
                    list = list.filter { it.media_type == ItemType.PERSON.type }
                )
            }
        }

        item {
            VerticalSpacer(heightDp = 24)
        }

        items(itemList) { item ->
            if (item?.media_type != ItemType.PERSON.type) {
                val movieItem = item?.toMovieItem()

                if (item?.poster_path != null && movieItem != null) {
                    MoviesListItemHorizontal(
                        model = movieItem,
                        type = movieItem.type
                    ) { id, type ->
                        onItemClick.invoke(id, type)
                    }
                }

                VerticalSpacer(heightDp = 16)
            }
        }
    }
}

@Preview
@Composable
fun PreviewMovieListVertical() {

}