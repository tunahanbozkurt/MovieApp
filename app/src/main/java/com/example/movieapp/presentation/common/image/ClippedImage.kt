package com.example.movieapp.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.movieapp.R

@Composable
fun ClippedImage(
    @DrawableRes drawableRes: Int,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(24.dp),
    imageWidth: Dp = 206.dp,
    imageHeight: Dp = 300.dp,
) {
    Image(
        painter = painterResource(id = drawableRes),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(imageWidth, imageHeight)
            .clip(shape = shape)
    )
}

@Preview
@Composable
fun PreviewClippedImage() {
    ClippedImage(R.drawable.onboarding_man_img)
}