package com.example.movieapp.presentation.home.elements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.movieapp.domain.model.PopularMovie
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.common.text.BlueText
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.GenreList
import com.example.movieapp.util.createImgUrl
import com.example.movieapp.util.pickGenre

@Composable
fun PopularMoviesList(
    popularMovieList: List<PopularMovie>,
    genreList: GenreList,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "Most popular",
                style = MaterialTheme.localFont.semiBoldH4,
                color = Color.White
            )
            BlueText(text = "See All", style = MaterialTheme.localFont.mediumH5)
        }

        VerticalSpacer(heightDp = 16)

        LazyRow {
            item { HorizontalSpacer(width = 24) }
            items(popularMovieList) { movie ->
                if (movie.poster_path != null) {
                    PopularMoviesListItem(
                        imgUrl = createImgUrl(movie.poster_path),
                        rate = movie.vote_average,
                        title = movie.original_title,
                        genre = pickGenre(genreList = genreList, movie = movie),
                    )
                    HorizontalSpacer(width = 12)
                }
            }
        }
    }
}