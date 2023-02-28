package com.example.movieapp.presentation.home.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.domain.model.MovieDetail
import com.example.movieapp.presentation.common.Rate
import com.example.movieapp.presentation.common.TopApplicationBar
import com.example.movieapp.presentation.common.button.ColorfulButton
import com.example.movieapp.presentation.common.icon.CircularIcon
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.IconWithText
import com.example.movieapp.ui.theme.Primary_Dark
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.createImgUrl

@Composable
fun DetailScreen(
    movieId: Int,
    viewModel: DetailScreenVM = hiltViewModel()
) {

    val movieItem = viewModel.movieDetailState.collectAsState().value
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.loadData(movieId)
    }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        MovieDetailHeadSection(movieItem)
        OverviewSection(movieItem, modifier = Modifier.padding(horizontal = 24.dp))
    }
}

@Composable
fun MovieFeatures(
    model: MovieDetail,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.padding(horizontal = 12.dp)
    ) {
        IconWithText(resId = R.drawable.ic_calendar, text = model.release_date)
        HorizontalSpacer(width = 12)
        Divider(
            color = MaterialTheme.localColor.textGrey,
            modifier = Modifier
                .width(1.dp)
                .height(12.dp)
        )
        HorizontalSpacer(width = 12)
        IconWithText(resId = R.drawable.ic_clock, text = "${model.runtime} Minutes")
        HorizontalSpacer(width = 12)
        Divider(
            color = MaterialTheme.localColor.textGrey,
            modifier = Modifier
                .width(1.dp)
                .height(12.dp)
        )
        HorizontalSpacer(width = 12)
        IconWithText(resId = R.drawable.ic_film, text = "EXAMPLE")
    }
}

@Composable
fun MovieDetailButtonSet(
    playButtonColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        ColorfulButton(color = playButtonColor)
        HorizontalSpacer(width = 16)
        CircularIcon(
            resId = R.drawable.ic_download,
            tint = MaterialTheme.localColor.primaryBlueAccent
        )
        HorizontalSpacer(width = 16)
        CircularIcon(resId = R.drawable.ic_share, tint = MaterialTheme.localColor.primaryBlueAccent)
    }
}

@Composable
fun OverviewSection(
    model: MovieDetail,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(text = "Story Line", style = MaterialTheme.localFont.semiBoldH4)
        VerticalSpacer(heightDp = 8)
        Text(
            text = model.overview,
            style = MaterialTheme.localFont.regularH5,
            color = MaterialTheme.localColor.textWhiteGrey
        )
    }
}

@Composable
fun MovieDetailHeadSection(
    item: MovieDetail,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.height(IntrinsicSize.Min)
    ) {
        Box {
            AsyncImage(
                model = createImgUrl(item.poster_path),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .drawWithCache {
                        onDrawWithContent {
                            drawContent()
                            drawRect(
                                Brush.verticalGradient(
                                    0.2f to Primary_Dark.copy(alpha = 0.8f),
                                    1f to Primary_Dark
                                )
                            )
                        }
                    }
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
            ) {

                VerticalSpacer(heightDp = 8)
                TopApplicationBar(
                    title = item.original_title,
                    isBackButtonVisible = true,
                    backGround = Color.Transparent,
                    modifier = Modifier.fillMaxWidth()
                ) {

                }

                VerticalSpacer(heightDp = 24)

                AsyncImage(
                    model = createImgUrl(item.poster_path),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(205.dp, 287.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                VerticalSpacer(heightDp = 16)

                MovieFeatures(
                    model = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                )
                VerticalSpacer(heightDp = 8)
                Rate(rate = item.vote_average)
                VerticalSpacer(heightDp = 24)
                MovieDetailButtonSet(MaterialTheme.localColor.secondaryOrange)
                VerticalSpacer(heightDp = 24)
            }
        }

    }
}

@Preview
@Composable
fun PreviewDetailScreen() {

}