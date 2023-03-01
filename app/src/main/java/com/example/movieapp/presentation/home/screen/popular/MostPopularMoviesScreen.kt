package com.example.movieapp.presentation.home.screen.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.list.MoviesListItemHorizontal
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
            .padding(horizontal = 24.dp)
    ) {
        items(paging) {
            if (it != null) {
                MoviesListItemHorizontal(
                    model = it
                ) {

                }
                VerticalSpacer(heightDp = 16)
            }
        }
    }
}

@Preview
@Composable
fun PreviewMostPopularMoviesScreen() {

}