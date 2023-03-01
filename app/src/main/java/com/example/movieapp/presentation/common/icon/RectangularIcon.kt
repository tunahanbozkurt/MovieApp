package com.example.movieapp.presentation.common.icon

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.ui.theme.localColor

@Composable
fun RectangularIcon(
    @DrawableRes iconResId: Int,
    backGroundColor: Color,
    modifier: Modifier = Modifier,
    iconTint: Color = Color.Black,
    shape: Shape = RoundedCornerShape(12.dp)
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(shape)
            .background(backGroundColor)
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = iconTint
        )
    }
}

@Preview
@Composable
fun PreviewRectangularIcon() {
    RectangularIcon(R.drawable.ic_close, backGroundColor = MaterialTheme.localColor.primaryDark)
}