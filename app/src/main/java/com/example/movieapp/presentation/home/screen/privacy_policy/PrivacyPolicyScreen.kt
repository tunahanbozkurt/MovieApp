package com.example.movieapp.presentation.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun PrivacyPolicyScreen(
    modifier: Modifier = Modifier
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxWidth()
            .padding(24.dp)
    ) {

        Text(text = stringResource(id = R.string.terms), style = MaterialTheme.localFont.semiBoldH5)

        VerticalSpacer(heightDp = 13)

        Text(
            text = stringResource(id = R.string.lorem_ipsum_extra_long),
            style = MaterialTheme.localFont.mediumH5,
            color = MaterialTheme.localColor.textGrey
        )

        VerticalSpacer(heightDp = 8)

        Text(
            text = stringResource(id = R.string.lorem_ipsum_extra_long),
            style = MaterialTheme.localFont.mediumH5,
            color = MaterialTheme.localColor.textGrey
        )

        VerticalSpacer(heightDp = 24)

        Text(
            text = stringResource(id = R.string.changes_to_service),
            style = MaterialTheme.localFont.semiBoldH5
        )

        VerticalSpacer(heightDp = 8)

        Text(
            text = stringResource(id = R.string.lorem_ipsum_extra_long),
            style = MaterialTheme.localFont.mediumH5,
            color = MaterialTheme.localColor.textGrey
        )

        VerticalSpacer(heightDp = 8)

        Text(
            text = stringResource(id = R.string.lorem_ipsum_extra_long),
            style = MaterialTheme.localFont.mediumH5,
            color = MaterialTheme.localColor.textGrey
        )
    }
}