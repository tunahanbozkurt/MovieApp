package com.example.movieapp.presentation.onboarding.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.CenterAlignedText
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun OnboardingSheet(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    Box(
        modifier = modifier
            .background(MaterialTheme.localColor.primaryDark)
    ) {
        Column(
            modifier = Modifier.padding(
                top = 46.dp,
                start = 55.dp,
                end = 55.dp
            )
        ) {
            CenterAlignedText(
                text = context.getString(R.string.lorem_ipsum_short),
                style = MaterialTheme.localFont.semiBoldH3
            )

            Spacer(modifier = Modifier.height(14.dp))

            CenterAlignedText(
                text = context.getString(R.string.lorem_ipsum_long),
                style = MaterialTheme.localFont.mediumH5,
                textColor = MaterialTheme.localColor.textGrey
            )
        }
    }
}

@Preview
@Composable
fun PreviewOnboardingSheet() {
    OnboardingSheet()
}