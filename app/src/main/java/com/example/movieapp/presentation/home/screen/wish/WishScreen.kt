package com.example.movieapp.presentation.home.screen.wish

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.R
import com.example.movieapp.presentation.common.Image
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.card.WishCard
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun WishScreen(
    viewModel: WishScreenVM = hiltViewModel(),
    navigate: (Int, String) -> Unit
) {

    val wishes = viewModel.wishFlow.collectAsState(listOf()).value

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VerticalSpacer(heightDp = 14)
        Text(
            text = stringResource(id = R.string.wishlist),
            style = MaterialTheme.localFont.semiBoldH4
        )
        VerticalSpacer(heightDp = 30)

        if (wishes.isEmpty()) {
            EmptyWishView(
                modifier = Modifier.fillMaxSize()
            )
        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(wishes) { item ->
                    WishCard(
                        imgUrl = item.backdrop,
                        genre = item.genre,
                        title = item.original_title,
                        media_type = item.media_type,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        onClick = {
                            navigate.invoke(item.id, item.media_type)

                        },
                        onDeleteWish = {
                            viewModel.deleteWish(item)
                        }
                    )

                    VerticalSpacer(heightDp = 16)
                }
            }
        }
    }
}

@Composable
fun EmptyWishView(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Image(resId = R.drawable.ic_folder_colored)

        VerticalSpacer(heightDp = 16)

        Text(
            text = stringResource(id = R.string.no_movie),
            style = MaterialTheme.localFont.semiBoldH4,
            textAlign = TextAlign.Center
        )

        VerticalSpacer(heightDp = 8)

        Text(
            text = stringResource(id = R.string.find_your_movie),
            style = MaterialTheme.localFont.mediumH6,
            color = MaterialTheme.localColor.textGrey,
            textAlign = TextAlign.Center
        )
    }
}


@Preview
@Composable
fun PreviewWishScreen() {
    WishScreen() { _, _ -> }
}