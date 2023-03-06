package com.example.movieapp.presentation.home.screen.search_result

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.data.remote.dto.multiSearch.MultiSearchResult
import com.example.movieapp.presentation.common.Image
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.SearchBar
import com.example.movieapp.presentation.home.elements.list.MovieListVertical
import com.example.movieapp.presentation.home.screen.detail.ItemType
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.extensions.createImgUrl

@Composable
fun SearchResultScreen(
    query: String,
    viewModel: SearchResultScreenVM = hiltViewModel(),
    onCancel: () -> Unit,
    navigate: (Int, String) -> Unit,
) {

    val searchState = viewModel.searchField.collectAsState().value
    val pagerState = viewModel.pagerFlow.collectAsState().value.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        if (query.isNotEmpty()) {
            viewModel.setQuery(query = query)
        }
    }

    Column(
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {

        VerticalSpacer(heightDp = 8)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
        ) {

            SearchBar(
                query = searchState.query,
                hint = stringResource(id = R.string.search),
                onValueChange = { viewModel.setQuery(it) },
                onSearch = {},
                modifier = Modifier.weight(1f)
            )

            HorizontalSpacer(width = 8)

            Text(
                text = stringResource(id = R.string.cancel),
                style = MaterialTheme.localFont.mediumH6,
                modifier = Modifier.clickable { onCancel.invoke() })
        }

        if (
            pagerState.itemCount == 0 &&
            searchState.query.isNotEmpty() &&
            pagerState.loadState.refresh !is LoadState.Loading
        ) {

            EmptySearchView(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        } else {

            VerticalSpacer(heightDp = 24)

            MovieListVertical(
                itemList = pagerState,
                list = pagerState.itemSnapshotList.items.filter { it.media_type == ItemType.PERSON.type }
            ) { id, type ->
                navigate.invoke(id, type)
            }
        }
    }
}

@Composable
fun ActorsList(
    list: List<MultiSearchResult>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.actors),
            style = MaterialTheme.localFont.semiBoldH4
        )

        VerticalSpacer(heightDp = 16)

        LazyRow {

            items(list) {
                if (it.profile_path != null) {
                    ActorListItem(url = it.profile_path, name = it.name)
                    HorizontalSpacer(width = 12)
                }
            }
        }
    }
}

@Composable
fun ActorListItem(
    url: String,
    name: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = createImgUrl(url),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(64.dp)
        )

        VerticalSpacer(heightDp = 8)

        Text(text = name, style = MaterialTheme.localFont.semiBoldH6)
    }
}

@Composable
fun EmptySearchView(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Image(resId = R.drawable.ic_search_colorful)

        VerticalSpacer(heightDp = 16)

        Text(
            text = stringResource(id = R.string.couldnt_find_movie),
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
fun PreviewSearchResultScreen() {
}