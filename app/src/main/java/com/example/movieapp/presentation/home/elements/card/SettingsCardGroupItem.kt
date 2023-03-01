package com.example.movieapp.presentation.home.elements

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

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