package com.example.movieapp.presentation.home.screen.search


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.R
import com.example.movieapp.domain.model.popular.MovieItem
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.FakeSearchBar
import com.example.movieapp.presentation.home.elements.GenreList
import com.example.movieapp.presentation.home.elements.MovieListHorizontal
import com.example.movieapp.presentation.home.elements.list.MoviesListItemHorizontal
import com.example.movieapp.presentation.home.screen.detail.ItemType
import com.example.movieapp.presentation.navigation.HomeScreen
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.extensions.addNavArgument

@Composable
fun SearchScreen(
    viewModel: SearchScreenVM = hiltViewModel(),
    onBackPressed: () -> Unit,
    navigate: (String) -> Unit
) {

    val latestSearchedMovie = viewModel.latestSearchedMovie.collectAsState(initial = null).value
    val recommendedMovies = viewModel.recommendedMovies.collectAsState().value
    val selectedGenre = viewModel.selectedGenre.collectAsState().value
    val scrollState = rememberScrollState()
    val lazyState = rememberLazyListState()

    BackHandler(true) {
        onBackPressed.invoke()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        VerticalSpacer(heightDp = 8)

        FakeSearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .clickable {
                    navigate.invoke(HomeScreen.SearchResult.route)
                }
        )

        VerticalSpacer(heightDp = 24)

        GenreList(
            selectedGenre = selectedGenre,
            modifier = Modifier.fillMaxWidth()
        ) {
            viewModel.setGenre(it)
        }

        VerticalSpacer(heightDp = 24)

        latestSearchedMovie?.let { entity ->

            Text(
                text = stringResource(id = R.string.today),
                style = MaterialTheme.localFont.semiBoldH4,
                modifier = Modifier.padding(start = 24.dp)
            )

            VerticalSpacer(heightDp = 16)

            MoviesListItemHorizontal(
                model = MovieItem(
                    id = entity.id,
                    genre_ids = entity.genre_ids,
                    original_title = entity.original_title,
                    poster_path = entity.poster_path,
                    vote_average = entity.vote_average,
                    release_date = entity.release_date,
                ),
                type = entity.type,
                modifier = Modifier.padding(horizontal = 24.dp)
            ) { id, type ->
                navigate.invoke(
                    HomeScreen.Detail.route.addNavArgument(id).addNavArgument(type)
                )
            }
            VerticalSpacer(heightDp = 95)

        }

        MovieListHorizontal(
            title = stringResource(id = R.string.recommended),
            movieItemList = recommendedMovies,
            selectedGenre = selectedGenre,
            seeAll = {
                navigate.invoke(
                    HomeScreen.RecommendedMovies.route.addNavArgument(
                        latestSearchedMovie?.id ?: SearchScreenVM.BASE_MOVIE_ID
                    )
                )
            },
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentSize(Alignment.BottomCenter)
                .padding(bottom = 15.dp)
        ) { id ->
            navigate.invoke(
                HomeScreen.Detail.route.addNavArgument(id).addNavArgument(ItemType.MOVIE.type)
            )
        }
    }
}

@Preview
@Composable
fun PreviewSearchScreen() {
    SearchScreen(onBackPressed = {}) {}
}