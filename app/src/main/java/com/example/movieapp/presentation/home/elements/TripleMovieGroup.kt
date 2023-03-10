package com.example.movieapp.presentation.home.elements

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.domain.model.upcoming.UpcomingMovie
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.extensions.createImgUrl

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TripleMovieGroup(
    movieList: List<UpcomingMovie>,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {

    val state = rememberLazyListState()
    val snapFlingBehavior = rememberSnapFlingBehavior(lazyListState = state)
    val firstVisibleItemIndex = remember { derivedStateOf { state.firstVisibleItemIndex } }
    val selectedItemIndex = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(firstVisibleItemIndex.value) {
        when (firstVisibleItemIndex.value) {
            0 -> {
                selectedItemIndex.value = 0
            }
            1 -> {
                selectedItemIndex.value = 1
            }
            2 -> {
                selectedItemIndex.value = 2
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyRow(
            state = state,
            flingBehavior = snapFlingBehavior,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxSize(),
        ) {

            item { HorizontalSpacer(width = 12) }

            itemsIndexed(movieList) { index, item ->
                TripleMovieGroupItem(movie = item, index = index) { id ->
                    onClick.invoke(id)
                }
                HorizontalSpacer(width = 12)
            }
        }

        VerticalSpacer(heightDp = 12)

        TripleProgressIndicator(selectedItemIndex.value)
    }
}

@Composable
fun TripleMovieGroupItem(
    movie: UpcomingMovie,
    index: Int,
    onClick: (Int) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick.invoke(movie.id) }
    ) {

        AsyncImage(
            model = createImgUrl(movie.imgUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(295.dp)
                .height(
                    if (index == 1) 154.dp else 138.dp
                )
        )

        Column(
            modifier = Modifier
                .width(295.dp)
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {

            Text(
                text = movie.title,
                style = MaterialTheme.localFont.semiBoldH4,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            VerticalSpacer(heightDp = 4)

            Text(
                text = movie.releaseDate,
                style = MaterialTheme.localFont.mediumH6,
            )
        }
    }
}

@Preview
@Composable
fun PreviewTripleMovieGroup() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.onboarding_man_img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(295.dp)
                .height(138.dp)
        )

        Column(
            modifier = Modifier
                .width(295.dp)
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = "Black Panther: Wakanda \nForever",
                style = MaterialTheme.localFont.semiBoldH4
            )
            VerticalSpacer(heightDp = 4)
            Text(
                text = "On March 2, 2022",
                style = MaterialTheme.localFont.mediumH6,
            )
        }
    }
}