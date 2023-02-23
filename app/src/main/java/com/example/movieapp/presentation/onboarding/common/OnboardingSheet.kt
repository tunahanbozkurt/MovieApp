package com.example.movieapp.presentation.onboarding.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.Image
import com.example.movieapp.presentation.common.TitleCouple
import com.example.movieapp.presentation.onboarding.OnboardingProgress
import com.example.movieapp.ui.theme.localColor

@Composable
fun OnboardingSheet(
    modifier: Modifier = Modifier,
    progress: OnboardingProgress = OnboardingProgress.FIRST,
    halfSizeInfo: Boolean = false,
    textPadding: PaddingValues = PaddingValues(0.dp),
    onClick: () -> Unit

) {
    val context = LocalContext.current

    Column(
        modifier = modifier.background(MaterialTheme.localColor.primaryDark),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
        ) {
            TitleCouple(
                bigTitle = context.getString(R.string.lorem_ipsum_short),
                infoText = context.getString(R.string.lorem_ipsum_long),
                infoColor = MaterialTheme.localColor.textGrey,
                verticalMargin = 14.dp,
                halfSizeInfo = halfSizeInfo,
                padding = textPadding,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Box(
            modifier = modifier
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    resId = when (progress) {
                        OnboardingProgress.FIRST -> {
                            R.drawable.onboarding_progress_1
                        }
                        OnboardingProgress.SECOND -> {
                            R.drawable.onboarding_progress_2
                        }
                        OnboardingProgress.THIRD -> {
                            R.drawable.onboarding_progress_3
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Image(
                    modifier = Modifier.clickable { onClick.invoke() },
                    resId = when (progress) {
                        OnboardingProgress.FIRST -> {
                            R.drawable.onboarding_next_button_1
                        }
                        OnboardingProgress.SECOND -> {
                            R.drawable.onboarding_next_button_2
                        }
                        OnboardingProgress.THIRD -> {
                            R.drawable.onboarding_next_button_3
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewOnboardingSheet() {
    OnboardingSheet() {}
}