package com.example.movieapp.presentation.home.screen.notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.card.NotificationCard

@Composable
fun NotificationScreen() {
    Column {
        VerticalSpacer(heightDp = 24)

        NotificationCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
    }
}


@Preview
@Composable
fun PreviewNotificationScreen() {
    NotificationScreen()
}