package com.example.movieapp.presentation.home.elements.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.BlueButton
import com.example.movieapp.presentation.common.button.LogOutButton
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.presentation.common.spacer.VerticalSpacer
import com.example.movieapp.presentation.common.text.CenterAlignedText
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun SignOutCard(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit,
    onLogOut: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(32.dp))
            .background(MaterialTheme.localColor.primarySoft)
            .padding(vertical = 29.dp, horizontal = 27.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_logout_question),
            modifier = Modifier.size(125.dp),
            contentDescription = null
        )

        VerticalSpacer(heightDp = 32)

        Text(
            text = stringResource(id = R.string.are_you_sure),
            style = MaterialTheme.localFont.semiBoldH3
        )

        VerticalSpacer(heightDp = 12)

        CenterAlignedText(
            text = stringResource(id = R.string.lorem_ipsum_long),
            style = MaterialTheme.localFont.regularH6,
            textColor = MaterialTheme.localColor.textGrey,
            modifier = Modifier.padding(horizontal = 48.dp)
        )

        VerticalSpacer(heightDp = 33)

        Row {
            LogOutButton(
                modifier = Modifier.weight(1f)
            ) {
                onLogOut.invoke()
            }
            HorizontalSpacer(width = 12)
            BlueButton(
                buttonText = stringResource(id = R.string.cancel),
                modifier = Modifier.weight(1f)
            ) {
                onCancel.invoke()
            }
        }
    }
}

@Preview
@Composable
fun PreviewSignOutCard() {
    SignOutCard(modifier = Modifier, {}, {})
}