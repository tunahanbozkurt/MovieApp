package com.example.movieapp.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.Image
import com.example.movieapp.presentation.common.ProfileImage
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun ProfileBar(
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {

        ProfileImage(resId = R.drawable.profile_image)

        Column(
            Modifier.padding(start = 16.dp)
        ) {

            Text(
                text = "Hello, Tiffany",
                style = MaterialTheme.localFont.semiBoldH4
            )

            VerticalSpacer(heightDp = 4)

            Text(
                text = "Let's stream your favorite movie",
                style = MaterialTheme.localFont.mediumH6,
                color = MaterialTheme.localColor.textGrey
            )
        }

        Image(
            resId = R.drawable.hearth_icon,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.CenterEnd)
                .size(32.dp)
        )
    }

}

@Preview
@Composable
fun PreviewProfileBar() {
    ProfileBar()
}