package com.example.movieapp.presentation.home.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.*

@Composable
fun HomeScreen(
    viewModel: HomeScreenVM = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val popularMovieList = viewModel.popularMovie.collectAsState().value
    val upcomingMovie = viewModel.upcomingMovies.collectAsState().value
    val selectedGenre = viewModel.selectedGenre.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
    ) {

        VerticalSpacer(heightDp = 8)

        ProfileBar()

        VerticalSpacer(heightDp = 32)

        SearchBar(
            query = "Search",
            hint = "",
            onValueChange = {},
            onSearch = {}
        )

        VerticalSpacer(heightDp = 24)

        TripleMovieGroup(upcomingMovie)

        VerticalSpacer(heightDp = 24)


        GenreList(
            selectedGenre = selectedGenre,
            modifier = Modifier.fillMaxWidth()
        ) {
            viewModel.setGenre(it)
        }

        VerticalSpacer(heightDp = 24)

        MovieListHorizontal(
            title = "Most popular",
            movieItemList = popularMovieList.results,
            seeAll = {},
            selectedGenre = selectedGenre,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 15.dp)
        )
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {

}