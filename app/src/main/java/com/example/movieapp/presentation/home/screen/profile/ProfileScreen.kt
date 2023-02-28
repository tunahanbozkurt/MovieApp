package com.example.movieapp.presentation.home.screen.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.ProfileImage
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun ProfileScreen() {

    SettingsCardGroup(
        modifier = Modifier.fillMaxWidth()
    )
}

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

@Composable
fun PremiumMemberCard() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.localColor.secondaryOrange)
            .padding(24.dp)
    ) {

        Box {
            Icon(
                painter = painterResource(id = R.drawable.ic_workspace),
                tint = MaterialTheme.localColor.textWhite,
                contentDescription = null
            )
        }
        HorizontalSpacer(width = 8)
        Column {
            Text(text = "Premium Member", style = MaterialTheme.localFont.semiBoldH4)
            VerticalSpacer(heightDp = 8)
            Text(
                text = "New movies are coming for you, Download Now!",
                style = MaterialTheme.localFont.regularH5
            )
        }
    }
}

@Composable
fun SettingsCardGroupItem(
    title: String,
    @DrawableRes iconResId: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(48.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.localColor.primarySoft)
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    tint = MaterialTheme.localColor.textGrey,
                    contentDescription = null
                )
            }

            HorizontalSpacer(width = 16)
            Text(text = title, style = MaterialTheme.localFont.mediumH5)
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_forward),
            tint = MaterialTheme.localColor.primaryBlueAccent,
            modifier = Modifier.padding(end = 4.dp),
            contentDescription = null
        )
    }
}

@Composable
fun SettingsCardGroup(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(text = "General", color = Color.White)

        VerticalSpacer(heightDp = 24)

        LazyColumn {
            item {
                SettingsCardGroupItem(
                    title = "Notification",
                    iconResId = R.drawable.ic_download,
                    modifier = modifier
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
}