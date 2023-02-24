package com.example.movieapp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.presentation.common.HorizontalSpacer
import com.example.movieapp.presentation.common.VerticalSpacer
import com.example.movieapp.util.GenreList
import com.example.movieapp.util.createImgUrl
import com.example.movieapp.util.getDataClassFromJson

@Composable
fun HomeScreen(
    viewModel: HomeScreenVM = hiltViewModel()
) {
    val context = LocalContext.current
    val popularMovieList = viewModel.popularMovie.collectAsState().value
    val genreList = remember { mutableStateOf(GenreList()) }

    LaunchedEffect(Unit) {
        genreList.value =
            context.getDataClassFromJson("genre.json", GenreList::class.java) ?: GenreList()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        VerticalSpacer(height = 8)

        ProfileBar()

        LazyRow{
            item { HorizontalSpacer(width = 24) }
            items(popularMovieList.list) { movie ->
                if (movie.poster_path != null) {
                    PopularMoviesListItem(
                        imgUrl = createImgUrl(movie.poster_path),
                        rate = movie.vote_average,
                        title = movie.original_title,
                        genre = genreList.value.genres.find {
                            it.id == movie.genre_ids[0]
                        }?.name ?: "",
                    )
                    HorizontalSpacer(width = 12)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}