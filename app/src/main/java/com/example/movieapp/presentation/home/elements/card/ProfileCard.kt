package com.example.movieapp.presentation.home.elements.card

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.ProfileImage
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun ProfileCard(
    imgBase64: String?,
    displayName: String,
    email: String,
    modifier: Modifier = Modifier,
    @DrawableRes iconResId: Int,
    onClickIcon: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .border(
                    width = 1.dp,
                    MaterialTheme.localColor.primarySoft,
                    RoundedCornerShape(16.dp)
                )
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp, end = 19.dp)
        ) {


            if (imgBase64 != null) {
                val decodedString: ByteArray = Base64.decode(imgBase64, Base64.DEFAULT)
                val decodedByte =
                    BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                if (decodedByte != null) {
                    Image(
                        bitmap = decodedByte.asImageBitmap(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(54.dp)
                            .clip(RoundedCornerShape(27.dp))
                    )
                } else {
                    ProfileImage(resId = R.drawable.profile_image)
                }
            } else {
                ProfileImage(resId = R.drawable.profile_image)
            }


            HorizontalSpacer(width = 16)

            Column(
                Modifier.weight(1f)
            ) {

                Text(
                    text = displayName,
                    maxLines = 1,
                    style = MaterialTheme.localFont.semiBoldH4
                )

                VerticalSpacer(heightDp = 4)

                Text(
                    text = email,
                    maxLines = 1,
                    style = MaterialTheme.localFont.mediumH6,
                    color = MaterialTheme.localColor.textGrey
                )
            }

            HorizontalSpacer(width = 18)

            Icon(
                painter = painterResource(id = iconResId),
                tint = MaterialTheme.localColor.primaryBlueAccent,
                contentDescription = null,
                modifier = Modifier.clickable { onClickIcon.invoke() }
            )
        }
    }
}