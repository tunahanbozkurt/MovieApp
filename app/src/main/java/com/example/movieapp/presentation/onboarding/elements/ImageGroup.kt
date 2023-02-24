package com.example.movieapp.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.movieapp.R
import com.example.movieapp.presentation.common.ClippedImage
import com.example.movieapp.ui.theme.localColor

@Composable
fun ImageGroup(
    modifier: Modifier = Modifier
) {

    val constraintSet = ConstraintSet {
        val middleImage = createRefFor("middleImage")
        val leftImage = createRefFor("leftImage")
        val rightImage = createRefFor("rightImage")

        constrain(middleImage) {
            top.linkTo(parent.top, margin = 10.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(leftImage) {
            end.linkTo(middleImage.start, margin = 30.dp)
            bottom.linkTo(parent.bottom, margin = 15.dp)
        }

        constrain(rightImage) {
            start.linkTo(middleImage.end, margin = 30.dp)
            bottom.linkTo(parent.bottom, margin = 15.dp)
        }
    }

    Box(
        modifier = modifier
            .background(MaterialTheme.localColor.textBlack)
    ) {
        ConstraintLayout(
            constraintSet,
            modifier = modifier
                .size(678.dp, 352.dp)
        ) {
            ClippedImage(
                drawableRes = R.drawable.onboarding_man_img,
                modifier = Modifier.layoutId("middleImage")
            )
            ClippedImage(
                drawableRes = R.drawable.onboarding_the_jungle,
                modifier = Modifier
                    .layoutId("leftImage")
                    .alpha(0.3f)
            )
            ClippedImage(
                drawableRes = R.drawable.onboarding_life_of_pi,
                modifier = Modifier
                    .layoutId("rightImage")
                    .alpha(0.3f)
            )
        }
    }
}

@Preview
@Composable
fun PreviewImageGroup() {
    ImageGroup()
}