package com.example.movieapp.presentation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun TextWithDivider(
    text: String,
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Divider(
            thickness = 1.dp,
            color = MaterialTheme.localColor.primarySoft,
            modifier = modifier.width(62.dp)
        )

        HorizontalSpacer(width = 8)

        Text(
            text = stringResource(id = R.string.sign_up_with),
            style = MaterialTheme.localFont.mediumH5,
            color = MaterialTheme.localColor.textGrey
        )

        HorizontalSpacer(width = 8)

        Divider(
            thickness = 1.dp,
            color = MaterialTheme.localColor.primarySoft,
            modifier = modifier.width(62.dp)
        )

    }
}

@Preview
@Composable
fun PreviewTextWithDivider() {
    TextWithDivider("or sign up with")
}