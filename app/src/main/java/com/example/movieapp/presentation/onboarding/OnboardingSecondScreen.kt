package com.example.movieapp.presentation.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.presentation.onboarding.common.OnboardingSheet

@Composable
fun OnboardingSecondScreen(
    modifier: Modifier = Modifier,
    navigate: () -> Unit
) {

    Column(
        modifier
    ) {

        ImageGroup(
            modifier = Modifier.weight(10f)
        )

        OnboardingSheet(
            progress = OnboardingProgress.SECOND,
            modifier = Modifier.weight(10.5f)
        ) {
            navigate.invoke()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewOnboardingSecondScreen() {
    OnboardingSecondScreen(){}
}

