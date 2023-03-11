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
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.movieapp.R
import com.example.movieapp.presentation.common.spacer.HorizontalSpacer
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.ui.theme.localFont

@Composable
fun BottomNavigation(
    navController: NavHostController,
    tabState: MutableState<String>,
    currentDestination: MutableState<String>,
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
            text = stringResource(id = R.string.home),
            isExpanded = tabState.value == "Home",
            iconResId = R.drawable.ic_home,
        ) {
            tabState.value = "Home"
            navController.apply {
                navController.navigate(HomeScreen.Home.route) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                }
            }
        }

        BottomNavigationItem(
            text = stringResource(id = R.string.search),
            isExpanded = tabState.value == "Search",
            iconResId = R.drawable.ic_search,
        ) {
            tabState.value = "Search"
            navController.apply {
                navController.navigate(HomeScreen.Search.route) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }

                }
            }
        }

        BottomNavigationItem(
            text = stringResource(id = R.string.wishlist),
            isExpanded = tabState.value == "Wishlist",
            iconResId = R.drawable.ic_heart,
        ) {
            tabState.value = "Wishlist"
            navController.apply {
                navController.navigate(HomeScreen.Wishlist.route) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }

                }

            }
        }

        BottomNavigationItem(
            text = stringResource(id = R.string.profile),
            isExpanded = tabState.value == "Profile",
            iconResId = R.drawable.ic_person,
        ) {
            tabState.value = "Profile"
            navController.apply {
                navController.navigate(HomeScreen.Profile.route) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
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
}