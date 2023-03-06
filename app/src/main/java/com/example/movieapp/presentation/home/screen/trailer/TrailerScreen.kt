package com.example.movieapp.presentation.home.screen.trailer

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.presentation.common.component.DetailScreenTopApplicationBar
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.common.text.pickGenre
import com.example.movieapp.presentation.home.elements.IconWithText
import com.example.movieapp.presentation.home.screen.detail.CastAndCrew
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.extensions.createImgUrl
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun TrailerScreen(
    id: Int,
    type: String,
    viewModel: TrailerScreenVM = hiltViewModel(),
) {

    val activity = LocalContext.current as Activity
    val scrollState = rememberScrollState()
    val videoState = viewModel.videoState.collectAsState().value
    val detailState = viewModel.movieDetailState.collectAsState().value
    val castCrew = viewModel.castAndCrewState.collectAsState().value
    val imageState = viewModel.imageUrls.collectAsState().value
    val playerRotationState = remember {
        mutableStateOf(YoutubePlayerRotation.DEFAULT)
    }
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val systemUiController = rememberSystemUiController()
    val isFullScreen = playerRotationState.value == YoutubePlayerRotation.FULLSCREEN


    LaunchedEffect(Unit) {
        viewModel.loadData(id, type)
    }

    BackHandler(enabled = isFullScreen) {
        playerRotationState.value = YoutubePlayerRotation.DEFAULT
    }

    DisposableEffect(isFullScreen) {
        systemUiController.isSystemBarsVisible = !isFullScreen
        onDispose { }
    }

    LaunchedEffect(playerRotationState.value) {
        if (playerRotationState.value == YoutubePlayerRotation.FULLSCREEN) {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    Column(
        modifier = Modifier
            .padding(
                horizontal =
                if (playerRotationState.value == YoutubePlayerRotation.DEFAULT) 24.dp
                else 0.dp
            )
    ) {

        if (!isFullScreen) {

            DetailScreenTopApplicationBar(
                title = stringResource(id = R.string.trailer),
                isBackButtonVisible = true,
                padding = PaddingValues(horizontal = 0.dp),
                onBackClicked = {
                    onBackPressedDispatcher?.onBackPressed()
                },
                onWishClick = {
                    viewModel.addWish(model = detailState, type = type)
                })

            VerticalSpacer(heightDp = 24)
        }

        YoutubePlayer(
            videoId = videoState,
            modifier = if (isFullScreen) Modifier.fillMaxSize()
            else Modifier,
            rotation = playerRotationState
        ) {
            playerRotationState.value = it
        }

        if (!isFullScreen) {
            VerticalSpacer(heightDp = 12)

            Column(
                modifier = Modifier.verticalScroll(scrollState)
            ) {
                Column {
                    Text(
                        text = detailState.original_title,
                        style = MaterialTheme.localFont.semiBoldH4
                    )
                    VerticalSpacer(heightDp = 8)
                    Row {
                        IconWithText(
                            text = detailState.release_date,
                            iconResId = R.drawable.ic_calendar
                        )
                        HorizontalSpacer(width = 12)
                        Divider(
                            modifier = Modifier
                                .height(16.dp)
                                .width(1.dp),
                            color = MaterialTheme.localColor.textGrey
                        )
                        HorizontalSpacer(width = 12)
                        IconWithText(
                            text = pickGenre(movie = detailState),
                            iconResId = R.drawable.ic_film
                        )
                    }
                }

                VerticalSpacer(heightDp = 32)

                Text(
                    text = stringResource(id = R.string.synopsis),
                    style = MaterialTheme.localFont.semiBoldH4
                )
                VerticalSpacer(heightDp = 8)
                Text(
                    text = detailState.overview,
                    style = MaterialTheme.localFont.regularH5
                )

                VerticalSpacer(heightDp = 24)

                CastAndCrew(
                    modelList = castCrew
                )

                VerticalSpacer(heightDp = 24)

                Text(
                    text = stringResource(id = R.string.galery),
                    style = MaterialTheme.localFont.semiBoldH4
                )

                VerticalSpacer(heightDp = 16)

                LazyRow {
                    if (imageState != null) {
                        items(imageState.backdrops) { item ->
                            AsyncImage(
                                model = createImgUrl(item.file_path),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )
                            HorizontalSpacer(width = 12)
                        }
                    }
                }
                VerticalSpacer(heightDp = 12)
            }
        }

    }


}

@Preview
@Composable
fun PreviewTrailerScreen() {

}