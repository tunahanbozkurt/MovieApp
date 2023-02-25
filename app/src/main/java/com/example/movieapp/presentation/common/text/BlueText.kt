package com.example.movieapp.presentation.common.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun BlueText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.localFont.semiBoldH4
) {
    Text(
        text = text,
        style = style,
        color = MaterialTheme.localColor.primaryBlueAccent,
        modifier = modifier
    )
}