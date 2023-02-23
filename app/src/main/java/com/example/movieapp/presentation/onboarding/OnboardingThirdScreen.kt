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
    modifier: Modifier = Modifier,
    navigate: () -> Unit
) {

    Column(
        modifier = modifier
            .background(MaterialTheme.localColor.textBlack)
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.onboarding_third),
            contentDescription = null,
            modifier = modifier.weight(10f)
        )

        OnboardingSheet(
            progress = OnboardingProgress.THIRD,
            halfSizeInfo = true,
            modifier = modifier.weight(9f)
        ) {
            navigate.invoke()
        }
    }
}

@Preview
@Composable
fun PreviewOnboardingThirdScreen() {
    OnboardingThirdScreen(){}
}