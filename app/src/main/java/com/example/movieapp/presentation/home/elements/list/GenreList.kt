package com.example.movieapp.presentation.home.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.data.remote.dto.genre.Genre
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont
import com.example.movieapp.util.GenreList
import com.example.movieapp.util.extensions.getDataClassFromJson

@Composable
fun GenreList(
    selectedGenre: Genre,
    modifier: Modifier = Modifier,
    isTextVisible: Boolean = true,
    onGenreChanged: (Genre) -> Unit
) {

    val genreList = rememberGenreList().value.genres


    Column(
        modifier = modifier
    ) {

        if (isTextVisible) {
            Text(
                text = stringResource(id = R.string.categories),
                style = MaterialTheme.localFont.semiBoldH4,
                modifier = Modifier.padding(start = 24.dp)
            )

            VerticalSpacer(heightDp = 15)
        }

        LazyRow(
            verticalAlignment = Alignment.CenterVertically
        ) {

            item {
                HorizontalSpacer(width = 24)
            }

            items(genreList) { genre ->
                GenreListItem(
                    genre = genre,
                    isSelected = (genre.id == selectedGenre.id)
                ) {
                    if (selectedGenre != genre) {
                        onGenreChanged.invoke(genre)
                    }
                }
            }
        }
    }
}

@Composable
fun GenreListItem(
    genre: Genre,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(31.dp)
            .widthIn(min = 80.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick.invoke() }
            .background(
                if (isSelected) MaterialTheme.localColor.primarySoft
                else MaterialTheme.localColor.primaryDark
            )
            .padding(vertical = 8.dp, horizontal = 12.dp)
    ) {

        Text(
            text = genre.name,
            style = MaterialTheme.localFont.mediumH6,
            color = if (isSelected) MaterialTheme.localColor.primaryBlueAccent
            else MaterialTheme.localColor.textWhite
        )
    }
}

@Composable
fun rememberGenreList(): MutableState<GenreList> {
    val context = LocalContext.current
    val genreList = remember { mutableStateOf(GenreList()) }

    LaunchedEffect(Unit) {
        val jsonName = if (Locale.current.language == "en") "genre.json" else "genre_turkish.json"
        genreList.value =
            context.getDataClassFromJson(
                jsonName,
                GenreList::class.java
            ) ?: GenreList()
    }

    return genreList
}

@Preview
@Composable
fun PreviewCategoriesList() {
    GenreListItem(Genre(0, "All"), isSelected = false) {}
}