package com.example.movieapp.presentation.common.image

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun SocialMediaIcon(
    @DrawableRes resId: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = resId),
        null,
        modifier = modifier
    )
}