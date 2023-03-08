package com.example.movieapp.presentation.home.elements.card

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun SettingsCardGroupItem(
    @StringRes titleResId: Int,
    tint: Color,
    modifier: Modifier = Modifier,
    @DrawableRes iconResId: Int,
    onClick: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.clickable { onClick.invoke(titleResId) },
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(48.dp)
                .weight(1f)
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.localColor.primarySoft)
            ) {

                Icon(
                    painter = painterResource(id = iconResId),
                    tint = tint,
                    modifier = Modifier.padding(4.dp),
                    contentDescription = null
                )
            }

            HorizontalSpacer(width = 16)

            Text(text = stringResource(id = titleResId), style = MaterialTheme.localFont.mediumH5)
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_forward),
            tint = MaterialTheme.localColor.primaryBlueAccent,
            modifier = Modifier
                .padding(end = 4.dp),
            contentDescription = null
        )
    }
}