package com.example.movieapp.presentation.home.elements

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.ui.theme.localColor


@Composable
fun ProfileImageWithIcon(
    profileImagePath: String?,
    @DrawableRes iconResId: Int,
    onClick: () -> Unit
) {

    val decodedString: ByteArray = Base64.decode(profileImagePath, Base64.DEFAULT)
    val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

    Box(
        contentAlignment = Alignment.Center
    ) {
        if (decodedByte != null) {
            Image(
                bitmap = decodedByte.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(72.dp)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.profile_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(72.dp)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(MaterialTheme.localColor.primarySoft)
                .border(width = 1.dp, color = MaterialTheme.localColor.primaryDark, CircleShape)
                .clickable { }
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                tint = MaterialTheme.localColor.primaryBlueAccent,
                contentDescription = null,
                modifier = Modifier.clickable { onClick.invoke() }
            )
        }
    }
}

@Preview
@Composable
fun PreviewProfileImageWithIcon() {
}