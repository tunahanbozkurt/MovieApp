package com.example.movieapp.presentation.common.icon

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.ui.theme.localColor

@Composable
fun CircularIcon(
    @DrawableRes resId: Int,
    tint: Color,
    modifier: Modifier = Modifier,
    backGroundColor: Color = MaterialTheme.localColor.primarySoft,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(backGroundColor)
            .clickable { onClick.invoke() }
    ) {
        Icon(
            painter = painterResource(id = resId),
            tint = tint,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun PreviewCircularIcon() {
    CircularIcon(tint = Color.Red, resId = R.drawable.ic_download) {}
}