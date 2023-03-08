package com.example.movieapp.presentation.home.elements.card

import androidx.compose.foundation.background
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.presentation.common.button.PlayButton
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.Rate
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.extensions.createImgUrl
import com.example.movieapp.util.extensions.uppercaseFirst

@Composable
fun WishCard(
    imgUrl: String,
    genre: String,
    title: String,
    media_type: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onDeleteWish: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.localColor.primarySoft)
            .clickable { onClick.invoke() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model = createImgUrl(imgUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .size(121.dp, 83.dp)
            )
            PlayButton()
        }

        HorizontalSpacer(width = 16)

        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = genre, style = MaterialTheme.localFont.mediumH6)
            VerticalSpacer(heightDp = 6)
            Text(text = title, style = MaterialTheme.localFont.semiBoldH5)
            VerticalSpacer(heightDp = 6)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (Locale.current.language == "en") media_type.uppercaseFirst() else {
                        if (media_type == "movie") "Film" else "Dizi"
                    },
                    style = MaterialTheme.localFont.mediumH6,
                    color = MaterialTheme.localColor.textGrey
                )
                HorizontalSpacer(width = 8)
                Rate(rate = 4.5)
                Icon(
                    painter = painterResource(id = R.drawable.ic_heart),
                    tint = MaterialTheme.localColor.secondaryRed,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.CenterEnd)
                        .clickable { onDeleteWish.invoke() }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewWishCard() {
    WishCard("", "", "", "", onClick = {}) {}
}