package com.example.movieapp.presentation.onboarding.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.R
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun OnboardingSheet(
    modifier: Modifier = Modifier,

) {

    Column(
        modifier = modifier.background(MaterialTheme.localColor.primaryDark),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VerticalSpacer(heightDp = 46)

        Text(
            text = stringResource(id = R.string.lorem_ipsum_short),
            style = MaterialTheme.localFont.semiBoldH3,
            textAlign = TextAlign.Center
        )

        VerticalSpacer(heightDp = 14)

        Text(
            text = stringResource(id = R.string.lorem_ipsum_long),
            style = MaterialTheme.localFont.mediumH5,
            color = MaterialTheme.localColor.textGrey,
            textAlign = TextAlign.Center
        )

        VerticalSpacer(heightDp = 200)

    }
}

@Preview
@Composable
fun PreviewOnboardingSheet() {
    OnboardingSheet()
}