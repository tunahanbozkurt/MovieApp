package com.example.movieapp.presentation.home.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.domain.model.cast_crew.CastCrew
import com.example.movieapp.domain.model.detail.MovieDetail
import com.example.movieapp.presentation.common.button.ColorfulButton
import com.example.movieapp.presentation.common.component.DetailScreenTopApplicationBar
import com.example.movieapp.presentation.common.icon.CircularIcon
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.MovieFeatures
import com.example.movieapp.presentation.home.elements.Rate
import com.example.movieapp.presentation.home.elements.card.SeasonPicker
import com.example.movieapp.presentation.home.elements.card.ShareCard
import com.example.movieapp.presentation.home.elements.card.TvSeriesEpisodeCard
import com.example.movieapp.presentation.navigation.HomeScreen
import com.example.movieapp.ui.theme.Primary_Dark
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.extensions.*
import com.skydoves.cloudy.Cloudy

@Composable
fun DetailScreen(
    movieId: Int,
    type: String,
    viewModel: DetailScreenVM = hiltViewModel(),
    navigate: (String) -> Unit
) {

    val movieItem = viewModel.movieDetailState.collectAsState().value
    val castAndCrew = viewModel.castAndCrewState.collectAsState().value
    val seasonDetailState = viewModel.seasonDetailState.collectAsState().value
    val scrollState = rememberScrollState()
    val showDialog = remember { mutableStateOf(false) }
    val showSeasonPicker = remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val selectedSeason = remember { mutableStateOf(1) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loadData(movieId, type)
    }

    Box {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {

            MovieDetailHeadSection(
                model = movieItem,
                onShareClick = { showDialog.setTrue() },
                navigate = {
                    navigate.invoke("")
                },
                onTrailerClick = {
                    navigate.invoke(
                        HomeScreen.Trailer.route.addNavArgument(movieItem.id).addNavArgument(type)
                    )
                },
                onWishClick = { movieDetail ->
                    viewModel.addWish(movieDetail, type)
                    context.showToast(R.string.wish_added)
                }
            )

            MovieDetailOverviewSection(
                movieItem,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            VerticalSpacer(heightDp = 24)

            CastAndCrew(modelList = castAndCrew, modifier = Modifier.padding(horizontal = 24.dp))

            VerticalSpacer(heightDp = 24)

            if (type == ItemType.SERIES.type) {

                VerticalSpacer(heightDp = 24)

                Text(
                    text = stringResource(id = R.string.episode),
                    style = MaterialTheme.localFont.semiBoldH4,
                    modifier = Modifier.padding(start = 24.dp)
                )

                VerticalSpacer(heightDp = 13)

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 24.dp)
                        .clickable { showSeasonPicker.setTrue() }
                ) {
                    Text(
                        text = "${stringResource(id = R.string.season)} ${selectedSeason.value}",
                        style = MaterialTheme.localFont.mediumH5
                    )
                    HorizontalSpacer(width = 5)
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_down),
                        tint = MaterialTheme.localColor.textWhite,
                        contentDescription = null
                    )
                }

                VerticalSpacer(heightDp = 12)

                if (seasonDetailState?.poster_path != null) {
                    repeat(seasonDetailState.episodes.size) {
                        TvSeriesEpisodeCard(
                            imageUrl = createImgUrl(
                                seasonDetailState.episodes[it].still_path ?: ""
                            ),
                            runtime = seasonDetailState.episodes[it].air_date,
                            episode = seasonDetailState.episodes[it].episode_number.toString(),
                            modifier = Modifier.padding(horizontal = 24.dp),
                            overview = seasonDetailState.episodes[it].overview
                        )
                        VerticalSpacer(heightDp = 16)
                    }
                }
            }
        }

        if (showDialog.value) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { showDialog.setFalse() }
            ) {
                Cloudy(radius = 25, modifier = Modifier.fillMaxSize()) {}
                ShareCard(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                        .padding(24.dp)
                ) {
                    showDialog.setFalse()
                }
            }
        }

        if (showSeasonPicker.value) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { }
            ) {
                Cloudy(radius = 25, modifier = Modifier.fillMaxSize()) {}
                SeasonPicker(seasonNumber = movieItem.number_of_seasons, modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f, false)
                    .padding(24.dp),
                    selectedSeason = selectedSeason.value,
                    onClose = {
                        showSeasonPicker.setFalse()
                    },
                    onClick = {
                        selectedSeason.value = it
                        viewModel.loadSeasonData(movieId, selectedSeason.value)
                        showSeasonPicker.setFalse()
                    }
                )
            }
        }
    }
}

@Composable
fun MovieDetailButtonSet(
    playButtonColor: Color,
    modifier: Modifier = Modifier,
    onShareClick: () -> Unit,
    onTrailerClick: () -> Unit,
) {
    Row(
        modifier = modifier
    ) {
        ColorfulButton(text = stringResource(id = R.string.trailer), color = playButtonColor) {
            onTrailerClick.invoke()
        }
        HorizontalSpacer(width = 16)
        CircularIcon(
            resId = R.drawable.ic_download,
            tint = MaterialTheme.localColor.primaryBlueAccent
        ) {

        }
        HorizontalSpacer(width = 16)
        CircularIcon(
            resId = R.drawable.ic_share,
            tint = MaterialTheme.localColor.primaryBlueAccent,
        ) {
            onShareClick.invoke()
        }
    }
}

@Composable
fun MovieDetailOverviewSection(
    model: MovieDetail,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {

        Text(
            text = stringResource(id = R.string.story_line),
            style = MaterialTheme.localFont.semiBoldH4
        )

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
    model: MovieDetail,
    modifier: Modifier = Modifier,
    navigate: () -> Unit,
    onShareClick: () -> Unit,
    onWishClick: (MovieDetail) -> Unit,
    onTrailerClick: () -> Unit

) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.height(IntrinsicSize.Min)
    ) {

        Box {
            AsyncImage(
                model = createImgUrl(model.poster_path),
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

                DetailScreenTopApplicationBar(
                    title = model.original_title,
                    isBackButtonVisible = true,
                    backGround = Color.Transparent,
                    onWishClick = {
                        onWishClick.invoke(model)
                    },
                    onBackClicked = {
                        navigate.invoke()
                    }
                )


                VerticalSpacer(heightDp = 24)

                AsyncImage(
                    model = createImgUrl(model.poster_path),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(205.dp, 287.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                VerticalSpacer(heightDp = 16)

                MovieFeatures(
                    model = model,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                )

                VerticalSpacer(heightDp = 8)

                Rate(rate = model.vote_average)

                VerticalSpacer(heightDp = 24)

                MovieDetailButtonSet(
                    MaterialTheme.localColor.secondaryOrange,
                    onShareClick = {
                        onShareClick.invoke()
                    },
                    onTrailerClick = {
                        onTrailerClick.invoke()
                    }
                )

                VerticalSpacer(heightDp = 24)

            }
        }
    }
}

@Composable
fun CastAndCrew(
    modelList: List<CastCrew>?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {

        if (modelList != null && modelList.any { it.profile_path != null }) {
            Text(
                text = stringResource(id = R.string.cast_crew),
                style = MaterialTheme.localFont.semiBoldH4,
            )

            VerticalSpacer(heightDp = 16)

            LazyRow {
                items(modelList) { item ->
                    if (item.profile_path != null) {
                        CastAndCrewItem(model = item)
                        HorizontalSpacer(width = 16)
                    }
                }
            }
        }
    }
}

@Composable
fun CastAndCrewItem(
    model: CastCrew
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = createImgUrl(model.profile_path ?: ""),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
        )

        HorizontalSpacer(width = 8)

        NameAndPosition(
            model
        )
    }
}

@Composable
fun NameAndPosition(
    model: CastCrew,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = model.name.uppercaseFirst(),
            style = MaterialTheme.localFont.semiBoldH5
        )

        VerticalSpacer(heightDp = 4)

        Text(
            text = model.position.uppercaseFirst(),
            style = MaterialTheme.localFont.mediumH7,
            color = MaterialTheme.localColor.textGrey
        )
    }
}

@Preview
@Composable
fun PreviewDetailScreen() {

}