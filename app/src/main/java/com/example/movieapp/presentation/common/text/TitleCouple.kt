package com.example.movieapp.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.movieapp.presentation.common.text.CenterAlignedText
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun TitleCouple(
    bigTitle: String,
    infoText: String,
    titleStyle: TextStyle = MaterialTheme.localFont.semiBoldH2,
    infoStyle: TextStyle = MaterialTheme.localFont.mediumH6,
    modifier: Modifier = Modifier,
    halfSizeInfo: Boolean = false,
    verticalMargin: Dp = 8.dp,
    padding: PaddingValues = PaddingValues(0.dp),
    titleColor: Color = MaterialTheme.localColor.textWhite,
    infoColor: Color = MaterialTheme.localColor.textWhiteGrey,
) {

    val constrains = ConstraintSet {

        val bigTitle = createRefFor("bigTitle")
        val infoText = createRefFor("infoText")

        constrain(infoText) {
            top.linkTo(bigTitle.bottom, verticalMargin)
            start.linkTo(bigTitle.start, margin = 14.dp)
            end.linkTo(bigTitle.end, margin = 14.dp)
            width = if (halfSizeInfo) Dimension.percent(0.65f)
            else Dimension.fillToConstraints
        }
    }

    ConstraintLayout(
        constrains,
        modifier = modifier
            .width(IntrinsicSize.Max)
            .padding(padding)
    ) {

        CenterAlignedText(
            text = bigTitle,
            style = titleStyle,
            textColor = titleColor,
            modifier = Modifier
                .layoutId("bigTitle")
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )

        CenterAlignedText(
            text = infoText,
            style = infoStyle,
            textColor = infoColor,
            modifier = Modifier.layoutId("infoText")
        )
    }
}

@Preview
@Composable
fun PreviewTitleCouple() {
    TitleCouple("Let's get started", "The latest movies and series are here")
}