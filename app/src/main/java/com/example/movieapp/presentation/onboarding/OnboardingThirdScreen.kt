package com.example.movieapp.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.R
import com.example.movieapp.presentation.onboarding.common.OnboardingSheet
import com.example.movieapp.ui.theme.localColor

@Composable
fun OnboardingThirdScreen(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .background(MaterialTheme.localColor.textBlack)
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.onboarding_third),
            contentDescription = null,
            modifier = modifier.weight(1f)
        )

        OnboardingSheet(
            modifier = modifier.weight(0.9f)
        )
    }
}

@Preview
@Composable
fun PreviewOnboardingThirdScreen() {
    OnboardingThirdScreen()
}