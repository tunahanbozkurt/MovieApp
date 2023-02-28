package com.example.movieapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun TopApplicationBar(
    title: String,
    modifier: Modifier = Modifier,
    isBackButtonVisible: Boolean = false,
    backGround: Color = MaterialTheme.localColor.primaryDark,
    onBackClicked: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(55.dp)
            .background(backGround)
    ) {

        if (isBackButtonVisible) {
            Image(
                R.drawable.ic_back_button,
                modifier = Modifier
                    .clickable { onBackClicked.invoke() }
                    .padding(start = 24.dp)
                    .align(Alignment.CenterStart)
            )
        }

        HorizontalSpacer(width = 32)

        Text(
            text = title,
            style = MaterialTheme.localFont.semiBoldH4,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun PreviewTopApplicationBar() {
    TopApplicationBar("MovieApp", isBackButtonVisible = true) {}
}