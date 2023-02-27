package com.example.movieapp.presentation.home.screen.search_result

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieapp.data.remote.dto.genre.Genre
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.MovieListVertical
import com.example.movieapp.presentation.home.elements.SearchBar
import com.example.movieapp.presentation.home.elements.rememberGenreList

@Composable
fun SearchResultScreen(
    viewModel: SearchResultScreenVM = hiltViewModel()
) {

    val searchState = viewModel.searchField.collectAsState().value
    val pagerState = viewModel.pagerFlow.collectAsState().value.collectAsLazyPagingItems()
    val genresList = rememberGenreList()

    Column {
        VerticalSpacer(heightDp = 8)
        Row {
            SearchBar(
                query = searchState.query,
                hint = "Hint",
                onValueChange = { viewModel.query(it) },
                onSearch = {}
            )
            Text(text = "Cancel")
        }
        VerticalSpacer(heightDp = 32)
        MovieListVertical(
            itemList = pagerState.itemSnapshotList.items,
            selectedGenre = Genre(0, "All")
        )
    }
}

@Preview
@Composable
fun PreviewSearchResultScreen() {
    SearchResultScreen()
}