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
import com.example.movieapp.util.extensions.uppercaseFirst

@Composable
fun EditProfileInfoSection(
    imgPath: String?,
    name: String,
    email: String,
    onEditClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileImageWithIcon(
            imgPath = imgPath,
            iconResId = R.drawable.ic_edit_pencil
        ) {
            onEditClick.invoke()
        }
        VerticalSpacer(heightDp = 21)
        Text(text = name.uppercaseFirst(), style = MaterialTheme.localFont.semiBoldH4)
        VerticalSpacer(heightDp = 8)
        Text(
            text = email,
            style = MaterialTheme.localFont.mediumH5,
            color = MaterialTheme.localColor.textGrey
        )
    }
}

@Preview
@Composable
fun PreviewEditProfileInfoSection() {
}