package com.example.movieapp.presentation.home.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.R
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.FakeSearchBar
import com.example.movieapp.presentation.home.elements.GenreList
import com.example.movieapp.presentation.home.elements.MovieListHorizontal
import com.example.movieapp.presentation.home.elements.TripleMovieGroup
import com.example.movieapp.presentation.home.elements.bar.ProfileBar
import com.example.movieapp.presentation.navigation.HomeScreen
import com.example.movieapp.util.addNavArgument
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun HomeScreen(
    viewModel: HomeScreenVM = hiltViewModel(),
    navigate: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    val popularMovieList = viewModel.popularMovie.collectAsState().value
    val topRatedMovieList = viewModel.topRatedMovies.collectAsState().value
    val upcomingMovie = viewModel.upcomingMovies.collectAsState().value
    val selectedGenre = viewModel.selectedGenre.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
    ) {

        VerticalSpacer(heightDp = 8)

        ProfileBar(
            displayName = Firebase.auth.currentUser?.displayName
                ?: stringResource(id = R.string.unknown)
        ) {
            navigate.invoke(HomeScreen.Wishlist.route)
        }

        VerticalSpacer(heightDp = 32)


        FakeSearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .clickable {
                    navigate.invoke(HomeScreen.SearchResult.route)
                }
        )

        VerticalSpacer(heightDp = 24)

        TripleMovieGroup(upcomingMovie) { id ->
            navigate.invoke(HomeScreen.Detail.route.addNavArgument(id).addNavArgument("movie"))
        }

        VerticalSpacer(heightDp = 24)


        GenreList(
            selectedGenre = selectedGenre,
            modifier = Modifier.fillMaxWidth()
        ) {
            viewModel.setGenre(it)
        }

        VerticalSpacer(heightDp = 24)

        MovieListHorizontal(
            title = stringResource(id = R.string.most_popular),
            movieItemList = popularMovieList.results,
            seeAll = { navigate.invoke(HomeScreen.MostPopularMovies.route) },
            selectedGenre = selectedGenre,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 15.dp),
            onItemClicked = { id ->
                navigate.invoke(HomeScreen.Detail.route.addNavArgument(id).addNavArgument("movie"))
            }
        )

        VerticalSpacer(heightDp = 24)

        MovieListHorizontal(
            title = stringResource(id = R.string.toprated),
            movieItemList = topRatedMovieList.results,
            seeAll = { navigate.invoke(HomeScreen.TopRatedMovies.route) },
            selectedGenre = selectedGenre,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 15.dp),
            onItemClicked = { id ->
                navigate.invoke(HomeScreen.Detail.route.addNavArgument(id).addNavArgument("movie"))
            }
        )
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {

}