package com.example.movieapp.presentation.home.elements

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.Image
import com.example.movieapp.presentation.common.ProfileImage
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.uppercaseFirst

@Composable
fun ProfileBar(
    displayName: String,
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {

        ProfileImage(resId = R.drawable.profile_image)

        Column(
            Modifier.padding(start = 16.dp)
        ) {
            stringResource(R.string.hello_name, displayName)

            Text(
                text = "Hello, ${displayName.uppercaseFirst()}",
                style = MaterialTheme.localFont.semiBoldH4
            )

            VerticalSpacer(heightDp = 4)

            Text(
                text = stringResource(id = R.string.profile_bar_info),
                style = MaterialTheme.localFont.mediumH6,
                color = MaterialTheme.localColor.textGrey
            )
        }

        Image(
            resId = R.drawable.hearth_icon,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.CenterEnd)
                .size(32.dp)
        )
    }
}

@Preview
@Composable
fun PreviewProfileBar() {
    ProfileBar("Tiffany")
}