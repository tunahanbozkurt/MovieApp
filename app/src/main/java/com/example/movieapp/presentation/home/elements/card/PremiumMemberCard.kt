package com.example.movieapp.presentation.home.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun PremiumMemberCard() {
    /*TODO*/

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.localColor.secondaryOrange)
            .padding(24.dp)
    ) {

        Box {
            Icon(
                painter = painterResource(id = R.drawable.ic_workspace),
                tint = MaterialTheme.localColor.textWhite,
                contentDescription = null
            )
        }

        HorizontalSpacer(width = 8)

        Column {

            Text(
                text = stringResource(id = R.string.premium_member),
                style = MaterialTheme.localFont.semiBoldH4
            )

            VerticalSpacer(heightDp = 8)

            Text(
                text = stringResource(id = R.string.premium_card_message),
                style = MaterialTheme.localFont.regularH5
            )
        }
    }
}