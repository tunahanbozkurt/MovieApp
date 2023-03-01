package com.example.movieapp.presentation.home.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.data.remote.dto.genre.Genre
import com.example.movieapp.domain.model.popular.MovieItem
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.common.text.BlueText
import com.example.movieapp.presentation.common.text.pickGenre
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.createImgUrl

@Composable
fun MovieListHorizontal(
    title: String,
    movieItemList: List<MovieItem>,
    modifier: Modifier = Modifier,
    selectedGenre: Genre,
    seeAll: () -> Unit,
    onItemClicked: (Int) -> Unit
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
                text = title,
                style = MaterialTheme.localFont.semiBoldH4,
                color = Color.White
            )
            BlueText(
                text = stringResource(id = R.string.see_all),
                style = MaterialTheme.localFont.mediumH5,
                modifier = Modifier.clickable { seeAll.invoke() })
        }

        VerticalSpacer(heightDp = 16)

        LazyRow {
            item { HorizontalSpacer(width = 24) }
            items(movieItemList) { movie ->

                val selectedMovieGenre = if (selectedGenre.id == 0) true
                else movie.genre_ids.any {
                    it == selectedGenre.id
                }

                if (movie.poster_path != null && selectedMovieGenre) {
                    MoviesListItemVertical(
                        imgUrl = createImgUrl(movie.poster_path),
                        rate = movie.vote_average,
                        title = movie.original_title,
                        id = movie.id,
                        genre = if (selectedGenre.id == 0) pickGenre(movie = movie)
                        else selectedGenre.name
                    ) { id ->
                        onItemClicked.invoke(id)
                    }
                    HorizontalSpacer(width = 12)
                }
            }
        }
    }
}