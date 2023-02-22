package com.example.movieapp.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.R
import com.example.movieapp.presentation.common.Image
import com.example.movieapp.presentation.onboarding.common.OnboardingSheet
import com.example.movieapp.ui.theme.localColor

@Composable
fun OnboardingFirstScreen(
    navigate: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(MaterialTheme.localColor.textBlack)
            .fillMaxSize()
    ) {

        Image(
            resId = R.drawable.onboarding_first,
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f)
        )

        OnboardingSheet(
            progress = OnboardingProgress.FIRST,
            halfSizeInfo = true,
            modifier = Modifier
                .weight(9f)
        ) {
            navigate.invoke()
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2_XL)
@Composable
fun PreviewOnboardingFirstScreen() {
    OnboardingFirstScreen() {}
}