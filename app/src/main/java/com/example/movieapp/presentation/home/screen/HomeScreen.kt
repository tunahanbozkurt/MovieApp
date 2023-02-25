package com.example.movieapp.presentation.home.screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.GenreList
import com.example.movieapp.presentation.home.elements.PopularMoviesList
import com.example.movieapp.presentation.home.elements.SearchBar
import com.example.movieapp.util.GenreList
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

        SearchBar(
            query = "Search",
            onValueChange = {},
            onSearch = {}
        )

        GenreList(
            genreList = genreList.value.genres,
            modifier = Modifier.fillMaxWidth()
        )

        VerticalSpacer(heightDp = 24)

        PopularMoviesList(
            popularMovieList = popularMovieList.list,
            genreList = genreList.value,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.BottomCenter)
                .padding(bottom = 15.dp)
        )
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}