package com.example.movieapp.presentation.home.elements

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.domain.model.MovieDetail
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.text.pickGenre
import com.example.movieapp.ui.theme.localColor

@Composable
fun MovieFeatures(
    model: MovieDetail,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(horizontal = 12.dp)
    ) {

        IconWithText(
            iconResId = R.drawable.ic_calendar,
            text = model.release_date
        )

        HorizontalSpacer(width = 12)

        Divider(
            color = MaterialTheme.localColor.textGrey,
            modifier = Modifier
                .width(1.dp)
                .height(12.dp)
        )

        HorizontalSpacer(width = 12)

        IconWithText(
            iconResId = R.drawable.ic_clock,
            text = stringResource(id = R.string.runtime, model.runtime)
        )

        HorizontalSpacer(width = 12)

        Divider(
            color = MaterialTheme.localColor.textGrey,
            modifier = Modifier
                .width(1.dp)
                .height(12.dp)

        )

        HorizontalSpacer(width = 12)

        IconWithText(
            iconResId = R.drawable.ic_film,
            text = pickGenre(movie = model)
        )
    }
}