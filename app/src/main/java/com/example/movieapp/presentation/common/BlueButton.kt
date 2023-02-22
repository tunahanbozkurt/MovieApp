package com.example.movieapp.presentation.onboarding.common

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun BlueButton(
    buttonText: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,

) {
    Button(
        modifier = modifier
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.localColor.primaryBlueAccent,
            contentColor = MaterialTheme.localColor.textWhite
        ),
        onClick = { onClick.invoke() }
    ) {

        Text(
            text = buttonText,
            style = MaterialTheme.localFont.mediumH4
        )
    }
}