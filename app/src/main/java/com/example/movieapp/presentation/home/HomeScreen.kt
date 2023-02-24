package com.example.movieapp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.presentation.common.VerticalSpacer

@Composable
fun HomeScreen() {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        VerticalSpacer(height = 8)

        ProfileBar()


    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}