package com.example.movieapp.presentation.home.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.ui.theme.MontserratFontFamily
import com.example.movieapp.ui.theme.localColor

@Composable
fun FakeSearchBar(
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .height(41.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.localColor.primarySoft)
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            tint = MaterialTheme.localColor.textGrey,
            modifier = Modifier
                .padding(start = 16.dp),
            contentDescription = null
        )

        HorizontalSpacer(width = 8)

        Text(
            text = stringResource(id = R.string.search_title),
            style = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = MaterialTheme.localColor.textGrey
            )
        )
    }
}

@Preview
@Composable
fun PreviewFakeSearchBar() {
    FakeSearchBar()
}