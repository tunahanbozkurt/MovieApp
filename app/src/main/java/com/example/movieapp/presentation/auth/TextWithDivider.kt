package com.example.movieapp.presentation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

        Spacer(modifier = Modifier.width(8.dp))
        
        Text(
            text = "Or Sign up with",
            style = MaterialTheme.localFont.mediumH5,
            color = MaterialTheme.localColor.textGrey
        )

        Spacer(modifier = Modifier.width(8.dp))

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