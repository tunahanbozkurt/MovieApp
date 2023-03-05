package com.example.movieapp.presentation.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.common.Image
import com.example.movieapp.presentation.home.elements.TripleProgressIndicator
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    navigate: () -> Unit
) {
    val pagerState = rememberPagerState()
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {

        HorizontalPager(count = 3, state = pagerState) {
            when (it) {
                0 -> {
                    OnboardingFirstScreen(
                    )
                }
                1 -> {
                    OnboardingSecondScreen(

                    )
                }
                2 -> {
                    OnboardingThirdScreen()
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp, vertical = 55.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TripleProgressIndicator(currentIndex = pagerState.currentPage)
            Image(resId =
            when (pagerState.currentPage) {
                0 -> {
                    R.drawable.onboarding_next_button_1
                }
                1 -> {
                    R.drawable.onboarding_next_button_2
                }
                2 -> {
                    R.drawable.onboarding_next_button_3
                }
                else -> {
                    R.drawable.onboarding_next_button_1
                }
            },
                modifier = Modifier.clickable {
                    scope.launch {
                        when (pagerState.currentPage) {
                            0 -> {
                                pagerState.animateScrollToPage(1)
                            }
                            1 -> {
                                pagerState.animateScrollToPage(2)
                            }
                            2 -> {
                                navigate.invoke()
                            }
                            else -> {
                                pagerState.animateScrollToPage(1)
                            }
                        }
                    }
                }
            )
        }
    }
}