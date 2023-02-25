package com.example.movieapp.presentation.home.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.HorizontalSpacer
import com.example.movieapp.ui.theme.MontserratFontFamily
import com.example.movieapp.ui.theme.localColor

@Composable
fun SearchBar(
    query: String,
    onValueChange: (String) -> Unit,
    onSearch: (String) -> Unit,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .height(41.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.localColor.primarySoft)
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            tint = MaterialTheme.localColor.textGrey,
            modifier = Modifier
                .padding(start = 16.dp)
                .clickable { onSearch.invoke(query) },
            contentDescription = null
        )

        HorizontalSpacer(width = 8)

        BasicTextField(
            value = query,
            textStyle = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = MaterialTheme.localColor.textGrey
            ),
            onValueChange = { onValueChange.invoke(it) }
        )
    }
}

@Preview
@Composable
fun PreviewSearchBar() {
    SearchBar("Search a title", {}) {

    }
}