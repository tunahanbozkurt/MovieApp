package com.example.movieapp.presentation.home.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.Image
import com.example.movieapp.presentation.common.Rate
import com.example.movieapp.presentation.common.button.ColorfulButton
import com.example.movieapp.presentation.common.icon.CircularIcon
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.IconWithText
import com.example.movieapp.ui.theme.Primary_Dark
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun DetailScreen() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Primary_Dark)
            .fillMaxSize()
    ) {


        Image(
            resId = R.drawable.onboarding_life_of_pi,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(205.dp, 287.dp)
                .clip(RoundedCornerShape(12.dp))
                .align(Alignment.CenterHorizontally)
        )

        VerticalSpacer(heightDp = 16)

        MovieFeatures(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )
        VerticalSpacer(heightDp = 8)
        Rate(rate = 4.5)
        VerticalSpacer(heightDp = 24)
        MovieDetailButtonSet(MaterialTheme.localColor.secondaryOrange)
        VerticalSpacer(heightDp = 24)
        OverviewSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
    }
}

@Composable
fun MovieFeatures(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.padding(horizontal = 12.dp)
    ) {
        IconWithText(resId = R.drawable.ic_calendar, text = "2021")
        HorizontalSpacer(width = 12)
        Divider(
            color = MaterialTheme.localColor.textGrey,
            modifier = Modifier
                .width(1.dp)
                .height(12.dp)
        )
        HorizontalSpacer(width = 12)
        IconWithText(resId = R.drawable.ic_clock, text = "148 Minutes")
        HorizontalSpacer(width = 12)
        Divider(
            color = MaterialTheme.localColor.textGrey,
            modifier = Modifier
                .width(1.dp)
                .height(12.dp)
        )
        HorizontalSpacer(width = 12)
        IconWithText(resId = R.drawable.ic_film, text = "Action")
    }
}

@Composable
fun MovieDetailButtonSet(
    playButtonColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        ColorfulButton(color = playButtonColor)
        HorizontalSpacer(width = 16)
        CircularIcon(
            resId = R.drawable.ic_download,
            tint = MaterialTheme.localColor.primaryBlueAccent
        )
        HorizontalSpacer(width = 16)
        CircularIcon(resId = R.drawable.ic_share, tint = MaterialTheme.localColor.primaryBlueAccent)
    }
}

@Composable
fun OverviewSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(text = "Story Line", style = MaterialTheme.localFont.semiBoldH4)
        VerticalSpacer(heightDp = 8)
        Text(
            text = "Originally a story from Archie Comics which started in 1941, Riverdale centres around a group of high school students who are shocked by the death of classmate, Jason Blossom. Together theyunravel the secrets of Riverdale and who More",
            style = MaterialTheme.localFont.regularH5,
            color = MaterialTheme.localColor.textWhiteGrey
        )
    }
}

@Preview
@Composable
fun PreviewDetailScreen() {
    DetailScreen()
}