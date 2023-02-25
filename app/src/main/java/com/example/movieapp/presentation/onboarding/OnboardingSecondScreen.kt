package com.example.movieapp.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.presentation.onboarding.elements.OnboardingProgress
import com.example.movieapp.presentation.onboarding.elements.OnboardingSheet
import com.example.movieapp.ui.theme.localColor

@Composable
fun OnboardingSecondScreen(
    modifier: Modifier = Modifier,
    navigate: () -> Unit
) {

    Column(
        modifier.background(MaterialTheme.localColor.textBlack)
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
    OnboardingSecondScreen() {}
}

