package com.example.movieapp.presentation.home.elements.list

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.domain.model.popular.MovieItem
import com.example.movieapp.presentation.common.Image
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.common.text.pickGenre
import com.example.movieapp.presentation.home.elements.IconWithText
import com.example.movieapp.presentation.home.elements.ImageWithRate
import com.example.movieapp.presentation.home.elements.PriceTag
import com.example.movieapp.presentation.home.elements.Rate
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.extensions.getYearFromDate
import com.example.movieapp.util.extensions.uppercaseFirst

@Composable
fun MoviesListItemHorizontal(
    model: MovieItem,
    modifier: Modifier = Modifier,
    type: String = stringResource(id = R.string.movie),
    onClick: (Int, String) -> Unit
) {
    /*TODO CLEANUP*/

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick.invoke(model.id, type.lowercase()) }
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            ImageWithRate(
                imgUrl = model.poster_path ?: "",
                model.vote_average
            )

            Rate(
                rate = model.vote_average, modifier = Modifier
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
            Text(
                text = model.original_title,
                style = MaterialTheme.localFont.semiBoldH4,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            VerticalSpacer(heightDp = 12)

            IconWithText(
                iconResId = R.drawable.ic_calendar,
                text = model.release_date.getYearFromDate()
            )
            VerticalSpacer(heightDp = 12)
            Row {
                IconWithText(iconResId = R.drawable.ic_film, text = pickGenre(movie = model))
                HorizontalSpacer(width = 8)
                Divider(
                    color = MaterialTheme.localColor.textGrey, modifier = Modifier
                        .width(1.dp)
                        .height(16.dp)
                )
                HorizontalSpacer(width = 8)
                Text(text = type.uppercaseFirst(), style = MaterialTheme.localFont.mediumH6)
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
            Text(
                text = "Spider-Man No Way..",
                style = MaterialTheme.localFont.semiBoldH4
            )
            VerticalSpacer(heightDp = 12)

            IconWithText(
                iconResId = R.drawable.ic_calendar,
                text = "2021"
            )
            VerticalSpacer(heightDp = 14)

            Row {
                IconWithText(
                    iconResId = R.drawable.ic_film,
                    text = stringResource(id = R.string.action)
                )
                HorizontalSpacer(width = 8)
                Divider(
                    color = MaterialTheme.localColor.textGrey, modifier = Modifier
                        .width(1.dp)
                        .height(16.dp)
                )
                HorizontalSpacer(width = 8)
                Text(
                    text = stringResource(id = R.string.movie),
                    style = MaterialTheme.localFont.mediumH6
                )
            }
        }
    }
}