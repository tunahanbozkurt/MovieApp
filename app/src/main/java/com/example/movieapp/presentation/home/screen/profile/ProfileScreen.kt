package com.example.movieapp.presentation.home.screen.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.ProfileImage
import com.example.movieapp.presentation.common.button.LogOutButton
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.ui.theme.Primary_Dark
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun ProfileScreen() {

    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Primary_Dark)
            .padding(horizontal = 24.dp)
            .verticalScroll(scrollState)
    ) {
        ProfileCard(
            displayName = "Tiffany",
            email = "example@gmail.com",
            iconResId = R.drawable.ic_edit
        )
        VerticalSpacer(heightDp = 24)

        SettingsCardGroup(
            title = "Account",
            settings = listOf(
                SettingModel("Member", R.drawable.ic_film),
                SettingModel("Change Password", R.drawable.ic_film),
            ),
            modifier = Modifier.fillMaxWidth()
        )
        VerticalSpacer(heightDp = 20)
        SettingsCardGroup(
            title = "General",
            settings = listOf(
                SettingModel("Notification", R.drawable.ic_film),
                SettingModel("Language", R.drawable.ic_film),
                SettingModel("Country", R.drawable.ic_film),
                SettingModel("Clear Cache", R.drawable.ic_film)
            ),
            modifier = Modifier.fillMaxWidth()
        )
        VerticalSpacer(heightDp = 20)
        SettingsCardGroup(
            title = "More",
            settings = listOf(
                SettingModel("Legal and Policies", R.drawable.ic_film),
                SettingModel("Help & Feedback", R.drawable.ic_film),
                SettingModel("About Us", R.drawable.ic_film)
            ),
            modifier = Modifier.fillMaxWidth()
        )
        VerticalSpacer(heightDp = 40)
        LogOutButton(
            modifier = Modifier.fillMaxWidth()
        ) {

        }
    }
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
            modifier = Modifier
                .height(48.dp)
                .weight(1f)
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
    title: String,
    settings: List<SettingModel>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.localColor.primaryDark)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.localColor.primarySoft
            )
            .padding(24.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.localFont.semiBoldH3
        )
        VerticalSpacer(heightDp = 24)
        repeat(settings.size) {
            SettingsCardGroupItem(
                title = settings[it].title,
                iconResId = settings[it].icon
            )
            if (it != settings.size - 1) {
                Divider(
                    color = MaterialTheme.localColor.primarySoft,
                    modifier = Modifier
                        .height(1.dp)
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {

}