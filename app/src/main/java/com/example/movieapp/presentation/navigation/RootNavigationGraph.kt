package com.example.movieapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    AnimatedNavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.ONBOARDING,
        modifier = modifier
    ) {
        onboardingNavGraph(navController = navController)
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val ONBOARDING = "onboarding_graph"
    const val AUTHENTICATION = "authentication_graph"
    const val HOME = "home_graph"
}