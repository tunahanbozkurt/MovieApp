package com.example.movieapp.presentation.home.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.presentation.common.Rate
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.shortenTitle

@Composable
fun MoviesListItemVertical(
    imgUrl: String,
    title: String,
    genre: String,
    rate: Double,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .size(135.dp, 231.dp)
            .wrapContentSize(Alignment.Center)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Column(
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imgUrl)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(width = 135.dp, height = 178.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentDescription = null
            )
            Column(
                Modifier
                    .background(MaterialTheme.localColor.primarySoft)
                    .fillMaxSize()
                    .padding(start = 8.dp, bottom = 8.dp, top = 12.dp)
            ) {
                Text(text = title.shortenTitle(), style = MaterialTheme.localFont.semiBoldH5)
                Text(
                    text = genre,
                    style = MaterialTheme.localFont.mediumH7,
                    color = MaterialTheme.localColor.textGrey
                )
            }
        }
        Rate(
            rate = rate,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 8.dp)
        )
    }
}

@Preview
@Composable
fun PreviewPopularMoviesList() {
    val modifier = Modifier
    Box(
        modifier = modifier
            .size(135.dp, 231.dp)
            .wrapContentSize(Alignment.Center)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Column(
        ) {
            Image(
                painter = painterResource(id = R.drawable.onboarding_the_jungle),
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(width = 135.dp, height = 178.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentDescription = null
            )
            Column(
                Modifier
                    .background(MaterialTheme.localColor.primarySoft)
                    .fillMaxSize()
                    .padding(start = 8.dp, bottom = 8.dp, top = 12.dp)
            ) {
                Text(
                    text = "Movie",
                    style = MaterialTheme.localFont.semiBoldH5,
                    maxLines = 1
                )
                Text(
                    text = "Action",
                    style = MaterialTheme.localFont.mediumH7,
                    color = MaterialTheme.localColor.textGrey
                )
            }
        }
        Rate(
            rate = 4.5,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 8.dp)
        )
    }
}