package com.example.movieapp.presentation.home.elements

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun IconWithText(
    text: String,
    @DrawableRes iconResId: Int,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.localFont.mediumH6,
    textColor: Color = MaterialTheme.localColor.textGrey,
    iconTint: Color = MaterialTheme.localColor.textGrey
) {
    Row(
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            tint = iconTint,
            contentDescription = null
        )

        HorizontalSpacer(width = 4)

        Text(
            text = text,
            color = textColor,
            style = textStyle
        )
    }
}