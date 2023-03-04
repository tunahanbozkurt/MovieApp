package com.example.movieapp.presentation.home.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.R
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun EditProfileInfoSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileImageWithIcon(iconResId = R.drawable.ic_edit_pencil)
        VerticalSpacer(heightDp = 21)
        Text(text = "Tiffany", style = MaterialTheme.localFont.semiBoldH4)
        VerticalSpacer(heightDp = 8)
        Text(
            text = "Tiffanyjearsey@gmail.com",
            style = MaterialTheme.localFont.mediumH5,
            color = MaterialTheme.localColor.textGrey
        )
    }
}

@Preview
@Composable
fun PreviewEditProfileInfoSection() {
    EditProfileInfoSection()
}