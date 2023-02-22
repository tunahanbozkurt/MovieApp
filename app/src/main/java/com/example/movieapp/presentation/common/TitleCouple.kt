package com.example.movieapp.presentation.common

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun TitleCouple(
    bigTitle: String,
    infoText: String,
    modifier: Modifier = Modifier
) {

    val constrains = ConstraintSet {

        val bigTitle = createRefFor("bigTitle")
        val infoText = createRefFor("infoText")

        constrain(infoText) {
            top.linkTo(bigTitle.bottom, margin = 8.dp)
            start.linkTo(bigTitle.start, margin = 14.dp)
            end.linkTo(bigTitle.end, margin = 14.dp)
            width = Dimension.fillToConstraints
        }
    }

    ConstraintLayout(
        constrains,
        modifier = modifier.width(IntrinsicSize.Max)
    ) {

        Text(
            text = bigTitle,
            style = MaterialTheme.localFont.semiBoldH2,
            modifier = Modifier.layoutId("bigTitle").fillMaxWidth().wrapContentSize(Alignment.Center)
        )

        Text(
            text = infoText,
            style = MaterialTheme.localFont.mediumH6,
            color = MaterialTheme.localColor.textWhiteGrey,
            textAlign = TextAlign.Center,
            modifier = Modifier.layoutId("infoText")
        )
    }
}

@Preview
@Composable
fun PreviewTitleCouple() {
    TitleCouple("Let's get started", "The latest movies and series are here")
}