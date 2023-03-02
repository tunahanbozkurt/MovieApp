package com.example.movieapp.presentation.home.screen.profile

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.movieapp.ui.theme.Grey

data class SettingModel(
    val title: String,
    @DrawableRes val icon: Int,
    val tint: Color = Grey
)
