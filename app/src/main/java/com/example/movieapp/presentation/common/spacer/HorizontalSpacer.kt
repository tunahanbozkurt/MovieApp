package com.example.movieapp.presentation.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalSpacer(
    width: Int,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.width(width.dp))
}