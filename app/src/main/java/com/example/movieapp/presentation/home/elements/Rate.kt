package com.example.movieapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun Rate(
    rate: Double,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .size(55.dp, 24.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.localColor.primarySoft.copy(alpha = 0.62f))

    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                start = 8.dp,
                end = 8.dp,
                top = 4.dp,
                bottom = 4.dp
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star),
                tint = MaterialTheme.localColor.secondaryOrange,
                contentDescription = null
            )

            HorizontalSpacer(width = 4)

            Text(
                text = rate.toString(),
                color = MaterialTheme.localColor.secondaryOrange,
                style = MaterialTheme.localFont.semiBoldBody
            )
        }
    }
}

@Preview
@Composable
fun PreviewRate() {
    Rate(4.5)
}