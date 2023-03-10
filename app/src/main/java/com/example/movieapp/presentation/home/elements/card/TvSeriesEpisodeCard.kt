package com.example.movieapp.presentation.home.elements.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.presentation.common.button.PlayButton
import com.example.movieapp.presentation.common.icon.CircularIcon
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.PriceTag
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.extensions.createImgUrl

@Composable
fun TvSeriesEpisodeCard(
    imageUrl: String,
    runtime: String,
    episode: String,
    overview: String,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.localColor.primarySoft)
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = createImgUrl(imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .size(121.dp, 83.dp)
                )
                PlayButton()
            }

            HorizontalSpacer(width = 16)

            Column(
                modifier = Modifier.weight(1f)
            ) {
                PriceTag(
                    isPremium = true,
                    backgroundColor = MaterialTheme.localColor.secondaryOrange
                )
                VerticalSpacer(heightDp = 4)
                Text(
                    text = runtime,
                    style = MaterialTheme.localFont.mediumH6,
                    color = MaterialTheme.localColor.textGrey
                )
                VerticalSpacer(heightDp = 4)
                Text(
                    text = "${stringResource(id = R.string.episode)} $episode",
                    style = MaterialTheme.localFont.semiBoldH5
                )
            }
            CircularIcon(
                resId = R.drawable.ic_download,
                backGroundColor = MaterialTheme.localColor.primaryDark,
                tint = MaterialTheme.localColor.secondaryOrange,
                modifier = Modifier.wrapContentSize(Alignment.CenterEnd)
            ) {}
        }
        VerticalSpacer(heightDp = 11)
        Text(
            text = overview,
            style = MaterialTheme.localFont.regularH5,
            color = MaterialTheme.localColor.textWhiteGrey
        )
    }
}

@Preview
@Composable
fun PreviewTvSeriesEpisodeCard() {
    TvSeriesEpisodeCard("212121", "1212", "12", "overv")
}