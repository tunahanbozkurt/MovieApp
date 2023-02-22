package com.example.movieapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.movieapp.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val MontserratFontFamily = FontFamily(
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold)
)

data class FontMontserrat(
    val semiBoldBody: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        color = Color.White
    ),
    val semiBoldH1: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        color = Color.White
    ),
    val semiBoldH2: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        color = Color.White
    ),
    val semiBoldH3: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        color = Color.White
    ),
    val semiBoldH4: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = Color.White
    ),
    val semiBoldH5: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        color = Color.White
    ),
    val semiBoldH6: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        color = Color.White
    ),
    val semiBoldH7: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        color = Color.White
    ),
    val mediumBody: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        color = Color.White
    ),
    val mediumH1: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        color = Color.White
    ),
    val mediumH2: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        color = Color.White
    ),
    val mediumH3: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        color = Color.White
    ),
    val mediumH4: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = Color.White
    ),
    val mediumH5: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = Color.White
    ),
    val mediumH6: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        color = Color.White
    ),
    val mediumH7: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        color = Color.White
    ),
    val regularBody: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = Color.White
    ),
    val regularH1: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        color = Color.White
    ),
    val regularH2: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        color = Color.White
    ),
    val regularH3: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = Color.White
    ),
    val regularH4: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.White
    ),
    val regularH5: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color.White
    ),
    val regularH6: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = Color.White
    ),
    val regularH7: TextStyle = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        color = Color.White,
    )
)

val LocalTypography = compositionLocalOf { FontMontserrat() }

val MaterialTheme.localFont: FontMontserrat
    @Composable
    @ReadOnlyComposable
    get() = LocalTypography.current

