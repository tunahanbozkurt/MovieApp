package com.example.movieapp.presentation.home.elements

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.ui.theme.localColor

@Composable
fun TripleProgressIndicator(
    currentIndex: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .size(
                    width = if (currentIndex == 0) 24.dp else 8.dp,
                    height = 8.dp
                )
                .clip(CircleShape)
                .alpha(if (currentIndex == 0) 1f else 0.32f)
                .background(MaterialTheme.localColor.primaryBlueAccent)
                .animateContentSize()
        )

        HorizontalSpacer(width = 8)

        Box(
            modifier = Modifier
                .size(
                    width = if (currentIndex == 1) 24.dp else 8.dp,
                    height = 8.dp
                )
                .clip(CircleShape)
                .alpha(if (currentIndex == 1) 1f else 0.32f)
                .background(MaterialTheme.localColor.primaryBlueAccent)
                .animateContentSize()

        )

        HorizontalSpacer(width = 8)

        Box(
            modifier = Modifier
                .size(
                    width = if (currentIndex == 2) 24.dp else 8.dp,
                    height = 8.dp
                )
                .clip(CircleShape)
                .alpha(if (currentIndex == 2) 1f else 0.32f)
                .background(MaterialTheme.localColor.primaryBlueAccent)
                .animateContentSize()
        )
    }
}

@Preview
@Composable
fun PreviewTripleProgressIndicator() {
    TripleProgressIndicator(0)
}