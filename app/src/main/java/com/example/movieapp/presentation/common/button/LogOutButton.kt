package com.example.movieapp.presentation.common.button

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun LogOutButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickable { onClick.invoke() }
            .height(56.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.localColor.primaryBlueAccent,
                shape = RoundedCornerShape(32.dp)
            )
    ) {
        Text(
            text = stringResource(id = R.string.log_out),
            style = MaterialTheme.localFont.semiBoldH4,
            color = MaterialTheme.localColor.primaryBlueAccent
        )
    }
}

@Preview
@Composable
fun PreviewLogOutButton() {
    LogOutButton {}
}