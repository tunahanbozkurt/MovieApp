package com.example.movieapp.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieapp.R
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@Composable
fun BottomNavigation(
    navController: NavHostController,
    tabState: String
) {

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(MaterialTheme.localColor.primaryDark)
    ) {

        BottomNavigationItem(
            text = "Home",
            isExpanded = tabState == "Home",
            iconResId = R.drawable.ic_home,
        ) {
            navController.apply {
                if (tabState != "Home") {
                    navigate(Graph.HOME)
                }
            }
        }

        BottomNavigationItem(
            text = "Search",
            isExpanded = tabState == "Search",
            iconResId = R.drawable.ic_search,
        ) {
            navController.apply {
                if (tabState != "Search") {
                    navigate(HomeScreen.Search.route)
                }
            }
        }

        BottomNavigationItem(
            text = "Download",
            isExpanded = tabState == "Download",
            iconResId = R.drawable.ic_download,
        ) {
            navController.apply {
                if (tabState != "Download") {
                    navigate(HomeScreen.Download.route)
                }
            }
        }

        BottomNavigationItem(
            text = "User",
            isExpanded = tabState == "Profile",
            iconResId = R.drawable.ic_person,
        ) {
            navController.apply {
                if (tabState != "Profile") {
                    navigate(HomeScreen.Profile.route)
                }
            }
        }
    }
}

@Composable
fun BottomNavigationItem(
    text: String,
    isExpanded: Boolean,
    modifier: Modifier = Modifier,
    @DrawableRes iconResId: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier.animateContentSize()
    ) {
        if (isExpanded) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .height(40.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onClick.invoke() }
                    .background(MaterialTheme.localColor.primarySoft)
                    .padding(vertical = 8.dp, horizontal = 12.dp)
                    .animateContentSize()
            ) {

                Icon(
                    painter = painterResource(id = iconResId),
                    tint = MaterialTheme.localColor.primaryBlueAccent,
                    contentDescription = null
                )

                HorizontalSpacer(width = 4)

                Text(
                    text = text,
                    style = MaterialTheme.localFont.mediumH6,
                    color = MaterialTheme.localColor.primaryBlueAccent
                )
            }
        } else {

            Icon(
                painter = painterResource(id = iconResId),
                tint = MaterialTheme.localColor.textGrey,
                modifier = Modifier.clickable { onClick.invoke() },
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun PreviewBottomNavigation() {
    BottomNavigation(navController = rememberAnimatedNavController(), "")
}