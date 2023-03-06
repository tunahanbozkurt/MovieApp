package com.example.movieapp.presentation.home.elements.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.icon.RectangularIcon
import com.example.movieapp.presentation.common.image.SocialMediaIcon
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun ShareCard(
    modifier: Modifier = Modifier,
    backGroundColor: Color = MaterialTheme.localColor.primarySoft,
    shape: Shape = RoundedCornerShape(16.dp),
    onCancel: () -> Unit

) {
    Column(
        modifier = modifier
            .clip(shape)
            .background(backGroundColor)
            .padding(
                vertical = 13.dp,
                horizontal = 19.dp
            )
    ) {

        RectangularIcon(
            iconResId = R.drawable.ic_close,
            backGroundColor = MaterialTheme.localColor.primaryDark,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.CenterEnd)
                .clickable { onCancel.invoke() }
        )

        VerticalSpacer(heightDp = 19)

        Text(
            text = stringResource(id = R.string.share_to),
            style = MaterialTheme.localFont.semiBoldH3,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        VerticalSpacer(heightDp = 16)

        Divider(
            color = MaterialTheme.localColor.primaryDark,
            modifier = Modifier.height(1.dp)
        )

        VerticalSpacer(heightDp = 32)

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            SocialMediaIcon(resId = R.drawable.ic_facebook)
            HorizontalSpacer(width = 16)
            SocialMediaIcon(resId = R.drawable.ic_instagram)
            HorizontalSpacer(width = 16)
            SocialMediaIcon(resId = R.drawable.ic_messenger)
            HorizontalSpacer(width = 16)
            SocialMediaIcon(resId = R.drawable.ic_telegram)
        }

        VerticalSpacer(heightDp = 51)
    }
}

@Preview
@Composable
fun PreviewShareCard() {
    ShareCard() {}
}