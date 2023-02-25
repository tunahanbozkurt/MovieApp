package com.example.movieapp.presentation.common.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(
    heightDp: Int,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.height(heightDp.dp))
}