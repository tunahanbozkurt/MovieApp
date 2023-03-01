package com.example.movieapp.presentation.home.elements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.Image
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun MoviesListItemHorizontal(
    imgUrl: String,
    title: String,
    year: String,
    genre: String,
    rate: Double,
    modifier: Modifier = Modifier,
    type: String = stringResource(id = R.string.movie)
) {
    /*TODO CLEANUP*/

    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            ImageWithRate(imgUrl = imgUrl, rate)

            Rate(
                rate = 4.5, modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            )
        }
        HorizontalSpacer(width = 16)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 4.dp)
        ) {
            PriceTag(isPremium = true, backgroundColor = MaterialTheme.localColor.secondaryOrange)
            VerticalSpacer(heightDp = 12)
            Text(text = title, style = MaterialTheme.localFont.semiBoldH4, maxLines = 1)
            VerticalSpacer(heightDp = 12)

            IconWithText(
                iconResId = R.drawable.ic_calendar,
                text = year
            )
            VerticalSpacer(heightDp = 12)
            Row {
                IconWithText(iconResId = R.drawable.ic_film, text = genre)
                HorizontalSpacer(width = 8)
                Divider(
                    color = MaterialTheme.localColor.textGrey, modifier = Modifier
                        .width(1.dp)
                        .height(16.dp)
                )
                HorizontalSpacer(width = 8)
                Text(text = type, style = MaterialTheme.localFont.mediumH6)
            }
        }
    }
}

@Preview
@Composable
fun PreviewMostPopularMoviesListItem() {
    Row {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                resId = R.drawable.onboarding_life_of_pi,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(112.dp, 147.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )

            Rate(
                rate = 4.5, modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            )
        }
        HorizontalSpacer(width = 16)
        Column {
            PriceTag(isPremium = true, backgroundColor = MaterialTheme.localColor.secondaryOrange)
            VerticalSpacer(heightDp = 12)
            Text(text = "Spider-Man No Way..", style = MaterialTheme.localFont.semiBoldH4)
            VerticalSpacer(heightDp = 12)

            IconWithText(
                iconResId = R.drawable.ic_calendar,
                text = "2021"
            )
            VerticalSpacer(heightDp = 14)

            Row {
                IconWithText(iconResId = R.drawable.ic_film, text = "Action")
                HorizontalSpacer(width = 8)
                Divider(
                    color = MaterialTheme.localColor.textGrey, modifier = Modifier
                        .width(1.dp)
                        .height(16.dp)
                )
                HorizontalSpacer(width = 8)
                Text(text = "Movie", style = MaterialTheme.localFont.mediumH6)
            }
        }
    }
}