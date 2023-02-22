package com.example.movieapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

data class LocalColors(
    val primaryDark: Color = Color(0xFF1F1D2B),
    val primarySoft: Color = Color(0xFF252836),
    val primaryBlueAccent: Color = Color(0xFF12CDD9),
    val secondaryGreen: Color = Color(0xFF22B07D),
    val secondaryOrange: Color = Color(0xFFFF8700),
    val secondaryRed: Color = Color(0xFFFB4141),
    val textBlack: Color = Color(0xFF171725),
    val textDarkGrey: Color = Color(0xFF696974),
    val textGrey: Color = Color(0xFF92929D),
    val textWhiteGrey: Color = Color(0xFFEBEBEF),
    val textWhite: Color = Color(0xFFFFFFFF),
    val textLineDark: Color = Color(0xFFEAEAEA),
)

val LocalColor = compositionLocalOf { LocalColors() }

val MaterialTheme.localColor: LocalColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColor.current