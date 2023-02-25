package com.example.movieapp.presentation.home.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.data.remote.dto.genre.Genre
import com.example.movieapp.presentation.common.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun GenreList(
    genreList: List<Genre>,
    modifier: Modifier = Modifier
) {

    val selectedGenre = remember {
        mutableStateOf(Genre(0, "All"))
    }

    Column(
        modifier = modifier
    ) {

        Text(
            text = "Categories",
            style = MaterialTheme.localFont.semiBoldH4,
            modifier = Modifier.padding(start = 24.dp)
        )

        VerticalSpacer(heightDp = 15)

        LazyRow(
            verticalAlignment = Alignment.CenterVertically
        ) {

            item {
                HorizontalSpacer(width = 24)
            }

            item {
                GenreListItem(
                    genre = Genre(0, "All"),
                    isSelected = selectedGenre.value.name == "All"
                ) {
                    selectedGenre.value = Genre(0, "All")
                }
            }
            items(genreList) {
                GenreListItem(
                    genre = it,
                    isSelected = (it.id == selectedGenre.value.id)
                ) {
                    selectedGenre.value = it
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

@Preview
@Composable
fun PreviewCategoriesList() {
    GenreListItem(Genre(0, "All"), isSelected = false) {}
}