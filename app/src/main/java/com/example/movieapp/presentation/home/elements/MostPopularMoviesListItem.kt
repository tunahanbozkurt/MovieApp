package com.example.movieapp.presentation.home.elements

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.presentation.common.Image
import com.example.movieapp.presentation.common.Rate
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.createImgUrl

@Composable
fun MostPopularMoviesListItem(
    imgUrl: String,
    title: String,
    year: String,
    runtimeMinute: Int,
    genre: String,
    rate: Double,
    type: String = "Movie"
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            ImageWithRate(imgUrl = createImgUrl(imgUrl), rate)

            Rate(
                rate = 4.5, modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            )
        }
        HorizontalSpacer(width = 16)
        Column(
            modifier = Modifier.fillMaxHeight().padding(vertical = 4.dp)
        ) {
            PriceTag(isPremium = true, backgroundColor = MaterialTheme.localColor.secondaryOrange)
            VerticalSpacer(heightDp = 12)
            Text(text = title, style = MaterialTheme.localFont.semiBoldH4, maxLines = 1)
            VerticalSpacer(heightDp = 12)

            IconWithText(
                resId = R.drawable.ic_calendar,
                text = year
            )
            VerticalSpacer(heightDp = 14)
            IconWithText(
                resId = R.drawable.ic_clock,
                text = "$runtimeMinute Minutes"
            )
            VerticalSpacer(heightDp = 12)
            Row {
                IconWithText(resId = R.drawable.ic_film, text = genre)
                HorizontalSpacer(width = 8)
                Divider(color = MaterialTheme.localColor.textGrey, modifier = Modifier
                    .width(1.dp)
                    .height(16.dp))
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
                resId = R.drawable.ic_calendar,
                text = "2021"
            )
            VerticalSpacer(heightDp = 14)
            IconWithText(
                resId = R.drawable.ic_clock,
                text = "148 Minutes"
            )
            VerticalSpacer(heightDp = 12)
            Row {
                IconWithText(resId = R.drawable.ic_film, text = "Action")
                HorizontalSpacer(width = 8)
                Divider(color = MaterialTheme.localColor.textGrey, modifier = Modifier
                    .width(1.dp)
                    .height(16.dp))
                HorizontalSpacer(width = 8)
                Text(text = "Movie", style = MaterialTheme.localFont.mediumH6)
            }
        }
    }
}

@Composable
fun PriceTag(
    isPremium: Boolean,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(backgroundColor)
            .size(65.dp, 20.dp)
    ) {
        Text(
            text = if (isPremium) "Premium" else "Free",
            style = MaterialTheme.localFont.mediumH7
        )
    }
}

@Composable
fun IconWithText(
    @DrawableRes resId: Int,
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.localFont.mediumH6,
    textColor: Color = MaterialTheme.localColor.textGrey,
    iconTint: Color = MaterialTheme.localColor.textGrey
) {
   Row(
       modifier = modifier
   ) {
       Icon(
           painter = painterResource(id = resId),
           tint = iconTint,
           contentDescription = null
       )
       HorizontalSpacer(width = 4)
       Text(
           text = text,
           color = textColor,
           style = textStyle
       )
   }
}

@Composable
fun ImageWithRate(
    imgUrl: String,
    rate: Double,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = imgUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .size(112.dp, 147.dp)
                .clip(RoundedCornerShape(8.dp)),
        )

        Rate(
            rate = rate, modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        )
    }
}