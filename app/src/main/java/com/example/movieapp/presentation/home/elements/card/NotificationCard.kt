package com.example.movieapp.presentation.home.elements.card

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun NotificationCard(
    modifier: Modifier = Modifier
) {
    val switchState = remember {
        mutableStateOf(true)
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                MaterialTheme.localColor.primarySoft,
                RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Text(
            text = "Messages Notifications",
            style = MaterialTheme.localFont.mediumH6,
            color = MaterialTheme.localColor.textGrey
        )
        VerticalSpacer(heightDp = 26)

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Messages Notifications",
                style = MaterialTheme.localFont.mediumH4,
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = switchState.value,
                onCheckedChange = { switchState.value = !switchState.value },
                colors = SwitchDefaults.colors(
                    checkedBorderColor = MaterialTheme.localColor.primaryBlueAccent,
                    checkedTrackColor = MaterialTheme.localColor.primaryBlueAccent,
                    uncheckedThumbColor = MaterialTheme.localColor.textWhite,
                    uncheckedBorderColor = MaterialTheme.localColor.primaryBlueAccent,
                    uncheckedTrackColor = MaterialTheme.localColor.textGrey
                )
            )
        }
        VerticalSpacer(heightDp = 18)

        Divider(
            color = MaterialTheme.localColor.textGrey,
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
        )
        VerticalSpacer(heightDp = 16)

        Text(
            text = "Exceptions",
            style = MaterialTheme.localFont.mediumH4
        )
    }
}

@Preview
@Composable
fun PreviewNotificationCard() {
    NotificationCard()
}