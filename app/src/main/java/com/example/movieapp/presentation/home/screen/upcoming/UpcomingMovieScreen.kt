package com.example.movieapp.presentation.home.screen.upcoming

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.Image
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.IconWithText
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun UpcomingMovieScreen() {
    /*TODO*/
}

@Composable
fun UpcomingMovieItem() {
    Column {
        Image(
            resId = R.drawable.onboarding_man_img,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .height(168.dp)
        )
        VerticalSpacer(heightDp = 12)
        Column {
            Text(text = "The Batman", style = MaterialTheme.localFont.semiBoldH4)
            Row {
                IconWithText(iconResId = R.drawable.ic_calendar, text = "March 2, 2022")
                HorizontalSpacer(width = 12)
                Divider(
                    color = MaterialTheme.localColor.textGrey,
                    modifier = Modifier
                        .width(1.dp)
                        .height(16.dp)
                )
                HorizontalSpacer(width = 12)
                IconWithText(iconResId = R.drawable.ic_film, text = "Action")
            }
        }
    }

}

@Preview
@Composable
fun PreviewUpcomingMovieScreen() {
    UpcomingMovieItem()
}