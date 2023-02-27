package com.example.movieapp.presentation.common.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun ColorfulButton(
    color: Color
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(48.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(color)
    ) {

        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 14.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                tint = MaterialTheme.localColor.textWhite,
                contentDescription = null
            )
            HorizontalSpacer(width = 8)
            Text(text = "Play", style = MaterialTheme.localFont.mediumH4)
        }
    }
}

@Preview
@Composable
fun PreviewOrangeButton() {
    ColorfulButton(MaterialTheme.localColor.secondaryOrange)
}