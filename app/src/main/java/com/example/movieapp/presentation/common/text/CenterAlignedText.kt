package com.example.movieapp.presentation.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.example.movieapp.ui.theme.localColor

@Composable
fun CenterAlignedText(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.localColor.textWhite
) {
    Text(
        text = text,
        style = style,
        color = textColor,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}