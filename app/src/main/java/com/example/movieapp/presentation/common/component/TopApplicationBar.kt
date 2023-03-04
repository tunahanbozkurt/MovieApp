package com.example.movieapp.presentation.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.Image
import com.example.movieapp.presentation.common.icon.RectangularIcon
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun TopApplicationBar(
    title: String,
    modifier: Modifier = Modifier,
    isBackButtonVisible: Boolean = false,
    backGround: Color = MaterialTheme.localColor.primaryDark,
    onBackClicked: () -> Unit
) {
    Box(
        modifier = modifier
            .height(55.dp)
            .background(backGround)
            .padding(horizontal = 24.dp)
    ) {

        if (isBackButtonVisible) {
            Image(
                R.drawable.ic_back_button,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable { onBackClicked.invoke() }
            )
        }

        Text(
            text = title,
            style = MaterialTheme.localFont.semiBoldH4,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.align(Alignment.Center)

        )
    }
}

@Composable
fun DetailScreenTopApplicationBar(
    title: String,
    modifier: Modifier = Modifier,
    isBackButtonVisible: Boolean = false,
    backGround: Color = MaterialTheme.localColor.primaryDark,
    padding: PaddingValues = PaddingValues(horizontal = 24.dp),
    onBackClicked: () -> Unit,
    onWishClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(backGround)
            .padding(padding)
    ) {

        if (isBackButtonVisible) {
            Image(
                R.drawable.ic_back_button,
                modifier = Modifier
                    .clickable { onBackClicked.invoke() }
                    .wrapContentSize(Alignment.CenterStart)
            )
        }

        Text(
            text = title,
            style = MaterialTheme.localFont.semiBoldH4,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .width(200.dp)
                .wrapContentSize(Alignment.Center)

        )

        RectangularIcon(
            iconResId = R.drawable.ic_heart,
            backGroundColor = MaterialTheme.localColor.primarySoft,
            iconTint = MaterialTheme.localColor.secondaryRed,
            modifier = Modifier
                .size(32.dp)
                .wrapContentSize(Alignment.CenterEnd)
                .clickable { onWishClick.invoke() }
        )
    }
}

@Preview
@Composable
fun PreviewTopApplicationBar() {
    TopApplicationBar("MovieApp", isBackButtonVisible = true, modifier = Modifier.fillMaxWidth()) {}
}