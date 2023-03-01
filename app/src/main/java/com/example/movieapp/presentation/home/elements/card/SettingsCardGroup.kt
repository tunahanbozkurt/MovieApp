package com.example.movieapp.presentation.home.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.screen.profile.SettingModel
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

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
            .padding(24.dp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.localColor.primarySoft
            )
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