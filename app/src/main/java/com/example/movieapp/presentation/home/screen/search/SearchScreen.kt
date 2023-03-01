package com.example.movieapp.presentation.home.screen.search


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.R
import com.example.movieapp.data.remote.dto.genre.Genre
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.GenreList
import com.example.movieapp.presentation.home.elements.MovieListHorizontal
import com.example.movieapp.presentation.home.elements.MoviesListItemHorizontal
import com.example.movieapp.presentation.home.elements.SearchBar
import com.example.movieapp.presentation.navigation.HomeScreen
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.addNavArgument

@Composable
fun SearchScreen(
    viewModel: SearchScreenVM = hiltViewModel(),
    navigate: (String) -> Unit
) {

    val searchFieldState = viewModel.searchField.collectAsState().value
    val recommendedMovies = viewModel.recommendedMovies.collectAsState().value
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        VerticalSpacer(heightDp = 8)

        SearchBar(
            query = searchFieldState.query,
            hint = stringResource(id = R.string.search_bar_hint),
            onValueChange = { viewModel.handleUIEvent(SearchScreenUIEvent.EnteredSearchQuery(it)) },
            onSearch = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        VerticalSpacer(heightDp = 24)

        GenreList(isTextVisible = false, selectedGenre = Genre(0, "All")) {} /*TODO*/

        VerticalSpacer(heightDp = 24)

        Text(
            text = stringResource(id = R.string.today),
            style = MaterialTheme.localFont.semiBoldH4,
            modifier = Modifier.padding(start = 24.dp)
        )

        VerticalSpacer(heightDp = 16)

        /*TODO*/
        MoviesListItemHorizontal(
            imgUrl = "/uJYYizSuA9Y3DCs0qS4qWvHfZg4.jpg",
            title = "The Avengers",
            year = "2012",
            genre = "Genre",
            rate = 3.0,
            modifier = Modifier.padding(start = 24.dp)
        )

        VerticalSpacer(heightDp = 95)

        MovieListHorizontal(
            title = stringResource(id = R.string.recommended),
            movieItemList = recommendedMovies,
            selectedGenre = Genre(0, "All"),
            seeAll = { /*TODO*/ },
            modifier = Modifier
                .padding(bottom = 15.dp)
        ) { id ->
            navigate.invoke(HomeScreen.Detail.route.addNavArgument(id))
        }
    }
}

@Preview
@Composable
fun PreviewSearchScreen() {
    SearchScreen {}
}