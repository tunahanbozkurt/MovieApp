package com.example.movieapp.presentation.home.screen.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.MoviesListItemHorizontal
import com.example.movieapp.ui.theme.localColor

@Composable
fun MostPopularMoviesScreen(
    viewModel: MostPopularMoviesScreenVM = hiltViewModel()
) {
    /*TODO*/
    val paging = viewModel.pager.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.localColor.primaryDark)
            .fillMaxSize()
    ) {
        items(paging) {
            MoviesListItemHorizontal(
                imgUrl = it?.poster_path ?: "",
                title = it?.original_title ?: "",
                year = it?.release_date ?: "",
                genre = "genre",
                rate = it?.vote_average ?: 1.0
            )
            VerticalSpacer(heightDp = 16)
        }
    }
}

@Preview
@Composable
fun PreviewMostPopularMoviesScreen() {

}