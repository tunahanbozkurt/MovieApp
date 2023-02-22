package com.example.movieapp.presentation.splash_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        delay(2000)
        /*TODO Navigate*/
    }

    SplashView(
        text = context.getString(R.string.cinemax),
        modifier = Modifier
            .background(MaterialTheme.localColor.primaryDark)
            .fillMaxSize()
    )
}

@Composable
fun SplashView(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.localColor.primaryBlueAccent,
    iconTintColor: Color = MaterialTheme.localColor.primaryBlueAccent
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_splash_icon),
            contentDescription = null,
            tint = iconTintColor
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = text,
            style = MaterialTheme.localFont.semiBoldH1,
            color = textColor
        )
    }
}

@Preview
@Composable
fun PreviewSplashScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_splash_icon),
            contentDescription = null,
            tint = MaterialTheme.localColor.primaryBlueAccent
        )

        Text(
            text = "CINEMAX",
            style = MaterialTheme.localFont.semiBoldH1,
            color = MaterialTheme.localColor.primaryBlueAccent
        )
    }
}