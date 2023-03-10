package com.example.movieapp.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.Image
import com.example.movieapp.presentation.onboarding.elements.OnboardingSheet
import com.example.movieapp.ui.theme.localColor

@Composable
fun OnboardingFirstScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(MaterialTheme.localColor.textBlack)
            .padding(top = 24.dp)
    ) {

        Image(
            resId = R.drawable.onboarding_first,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
        )

        OnboardingSheet()
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2_XL)
@Composable
fun PreviewOnboardingFirstScreen() {
    OnboardingFirstScreen()
}