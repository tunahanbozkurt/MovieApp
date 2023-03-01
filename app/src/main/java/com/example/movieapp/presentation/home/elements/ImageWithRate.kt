package com.example.movieapp.presentation.home.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.util.createImgUrl

@Composable
fun ImageWithRate(
    imgUrl: String,
    rate: Double,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = createImgUrl(imgUrl),
            contentScale = contentScale,
            contentDescription = null,
            modifier = Modifier
                .size(112.dp, 147.dp)
                .clip(RoundedCornerShape(8.dp)),
        )

        Rate(
            rate = rate,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        )
    }
}