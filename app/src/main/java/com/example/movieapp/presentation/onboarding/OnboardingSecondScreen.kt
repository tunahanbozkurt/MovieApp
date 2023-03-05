package com.example.movieapp.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.Image
import com.example.movieapp.presentation.onboarding.elements.OnboardingSheet
import com.example.movieapp.ui.theme.localColor

@Composable
fun OnboardingSecondScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.localColor.textBlack)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .background(MaterialTheme.localColor.textBlack)
                .height(421.dp)
                .fillMaxWidth()
        ) {
            Image(
                resId = R.drawable.onboarding_the_jungle,
                modifier = Modifier
                    .padding(top = 55.dp)
                    .clip(RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp))
                    .size(
                        70.dp,
                        300.dp
                    )
                    .alpha(0.3f),
                contentScale = ContentScale.Crop
            )
            Image(
                resId = R.drawable.onboarding_man_img,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .size(
                        200.dp,
                        300.dp
                    )
            )
            Image(
                resId = R.drawable.onboarding_life_of_pi,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 55.dp)
                    .clip(RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp))
                    .size(
                        40.dp,
                        300.dp
                    )
                    .alpha(0.3f)
            )
        }
        OnboardingSheet(
            modifier = Modifier
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewOnboardingSecondScreen() {
    OnboardingSecondScreen()
}

