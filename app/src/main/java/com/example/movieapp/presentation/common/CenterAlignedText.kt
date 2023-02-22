package com.example.movieapp.presentation.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CenterAlignedText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = style,
        modifier = modifier
    )
}