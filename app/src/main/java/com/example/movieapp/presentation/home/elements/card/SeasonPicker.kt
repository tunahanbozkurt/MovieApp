package com.example.movieapp.presentation.home.elements.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.movieapp.R
import com.example.movieapp.presentation.common.icon.RectangularIcon
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun SeasonPicker(
    seasonNumber: Int,
    selectedSeason: Int,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
    onClose: () -> Unit
) {

    val constraintSet = ConstraintSet {
        val list = createRefFor("list")
        val close = createRefFor("close")

        constrain(close) {
            top.linkTo(parent.top)
            end.linkTo(parent.end)
        }
        constrain(list) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }

    ConstraintLayout(
        constraintSet = constraintSet,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.localColor.primarySoft)
    ) {

        RectangularIcon(
            iconResId = R.drawable.ic_close,
            backGroundColor = MaterialTheme.localColor.primaryDark,
            iconTint = MaterialTheme.localColor.textGrey,
            modifier = Modifier
                .layoutId("close")
                .padding(top = 13.dp, end = 19.dp)
                .clickable { onClose.invoke() }
        )

        LazyColumn(
            Modifier
                .layoutId("list")
                .height(216.dp)
        ) {
            items(seasonNumber) {
                Text(
                    text = "Season ${it.plus(1)}",
                    style = MaterialTheme.localFont.semiBoldH2,
                    color = if (it.plus(1) == selectedSeason) MaterialTheme.localColor.textWhite
                    else MaterialTheme.localColor.textDarkGrey,
                    modifier = Modifier.clickable { onClick.invoke(it.plus(1)) }
                )
                VerticalSpacer(heightDp = 24)
            }
        }

    }
}

@Preview
@Composable
fun PreviewSeasonPicker() {

}