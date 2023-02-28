package com.example.movieapp.presentation.home.screen.search_result

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieapp.R
import com.example.movieapp.data.remote.dto.genre.Genre
import com.example.movieapp.presentation.common.Image
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.home.elements.MovieListVertical
import com.example.movieapp.presentation.home.elements.SearchBar
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun SearchResultScreen(
    query: String,
    viewModel: SearchResultScreenVM = hiltViewModel(),
    onCancel: () -> Unit,
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
                hint = "Search",
                onValueChange = { viewModel.setQuery(it) },
                onSearch = {},
                modifier = Modifier.weight(1f)
            )
            HorizontalSpacer(width = 8)
            Text(
                text = "Cancel",
                style = MaterialTheme.localFont.mediumH6,
                modifier = Modifier.clickable { onCancel.invoke() })
        }

        if (pagerState.itemCount == 0 && pagerState.loadState.refresh !is LoadState.Loading) {

            EmptySearchView(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        } else {
            VerticalSpacer(heightDp = 32)

            MovieListVertical(
                itemList = pagerState.itemSnapshotList.items,
                selectedGenre = Genre(0, "All")
            )
        }
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
            text = "We Are Sorry, We Can Not Find The Movie :(",
            style = MaterialTheme.localFont.semiBoldH4,
            textAlign = TextAlign.Center
        )
        VerticalSpacer(heightDp = 8)
        Text(
            text = "Find your movie by Type title, categories, years, etc",
            style = MaterialTheme.localFont.mediumH6,
            color = MaterialTheme.localColor.textGrey,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewSearchResultScreen() {
    SearchResultScreen(query = "") {}
}