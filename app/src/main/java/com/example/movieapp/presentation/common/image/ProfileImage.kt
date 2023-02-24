package com.example.movieapp.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R

@Composable
fun ProfileImage(
    @DrawableRes resId: Int,
    modifier: Modifier = Modifier,
) {

    Image(
        resId = resId,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(54.dp)
            .clip(RoundedCornerShape(27.dp))
    )
}

@Preview
@Composable
fun PreviewProfileImage() {
    ProfileImage(R.drawable.profile_image)
}