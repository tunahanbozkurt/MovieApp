package com.example.movieapp.presentation.home.elements

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.ProfileImage
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun ProfileCard(
    displayName: String,
    email: String,
    @DrawableRes iconResId: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp, end = 19.dp)
    ) {

        ProfileImage(resId = R.drawable.profile_image)

        HorizontalSpacer(width = 16)

        Column {

            Text(
                text = displayName,
                maxLines = 1,
                style = MaterialTheme.localFont.semiBoldH4
            )

            VerticalSpacer(heightDp = 4)

            Text(
                text = email,
                maxLines = 1,
                style = MaterialTheme.localFont.mediumH6,
                color = MaterialTheme.localColor.textGrey
            )
        }

        HorizontalSpacer(width = 18)

        Icon(
            painter = painterResource(id = iconResId),
            tint = MaterialTheme.localColor.primaryBlueAccent,
            contentDescription = null
        )
    }
}