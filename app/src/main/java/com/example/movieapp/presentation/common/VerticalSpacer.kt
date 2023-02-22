package com.example.movieapp.presentation.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(
    height: Int,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.height(height.dp))
}