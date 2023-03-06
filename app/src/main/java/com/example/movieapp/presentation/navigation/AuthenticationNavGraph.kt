package com.example.movieapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.example.movieapp.R
import com.example.movieapp.presentation.auth.base.AuthenticationScreen
import com.example.movieapp.presentation.auth.login.LoginScreen
import com.example.movieapp.presentation.auth.login.MessagePopup
import com.example.movieapp.presentation.auth.reset.ResetPasswordScreen
import com.example.movieapp.presentation.auth.signup.SignUpScreen
import com.example.movieapp.presentation.common.component.TopApplicationBar
import com.example.movieapp.presentation.home.screen.PrivacyPolicyScreen
import com.example.movieapp.util.extensions.setFalse
import com.example.movieapp.util.extensions.setTrue
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationNavGraph(
    modifier: Modifier,
    rootNavController: NavHostController,
    navController: NavHostController = rememberAnimatedNavController()
) {
    val topBarInfo = remember { mutableStateOf(TopBarInfo()) }
    val showPopup = remember { mutableStateOf(false) }
    val errorMsg = remember {
        mutableStateOf<Int?>(null)
    }

    DisposableEffect(Unit) {
        val listener =
            NavController.OnDestinationChangedListener { _, destination, _ ->
                topBarInfo.value = topBarInfo.value.copy(
                    title = makeTitle(destination),
                    isVisible = (destination.route != AuthenticationScreen.Authentication.route)
                )
            }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    Box {
        Scaffold(
            topBar = {
                if (topBarInfo.value.isVisible) {
                    TopApplicationBar(
                        title = topBarInfo.value.title,
                        isBackButtonVisible = true,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        navController.popBackStack()
                    }
                }

            },
            modifier = modifier
        ) {
            AnimatedNavHost(
                navController = navController,
                startDestination = AuthenticationScreen.Authentication.route,
                route = Graph.AUTHENTICATION,
                modifier = modifier.padding(it)
            ) {
                composable(
                    route = AuthenticationScreen.Authentication.route
                ) {
                    AuthenticationScreen { route ->
                        when (route) {
                            AuthenticationScreen.SignUp.route -> {
                                navController.navigate(route)
                            }
                            AuthenticationScreen.Login.route -> {
                                navController.navigate(route)
                            }
                            else -> {
                                rootNavController.navigate(route = route) {
                                    popUpTo(Graph.AUTHENTICATION) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    }
                }

                composable(
                    AuthenticationScreen.SignUp.route
                ) {
                    SignUpScreen(
                        showPopup = {
                            errorMsg.value = it
                            showPopup.setTrue()
                        }
                    ) { route ->
                        if (route == HomeScreen.PrivacyPolicy.route) {
                            navController.navigate(route)
                        } else {
                            rootNavController.navigate(route) {
                                popUpTo(Graph.AUTHENTICATION) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }

                composable(
                    AuthenticationScreen.Login.route
                ) {
                    LoginScreen(
                        showPopup = {
                            errorMsg.value = it
                            showPopup.setTrue()
                        }
                    ) { route ->
                        when (route) {
                            Graph.HOME -> {
                                rootNavController.navigate(route) {
                                    popUpTo(Graph.AUTHENTICATION) {
                                        inclusive = true
                                    }
                                }
                            }
                            AuthenticationScreen.ResetPassword.route -> {
                                navController.navigate(route)
                            }
                        }
                    }
                }

                composable(AuthenticationScreen.ResetPassword.route) {
                    ResetPasswordScreen {
                        /*TODO*/
                    }
                }

                composable(HomeScreen.PrivacyPolicy.route) {
                    PrivacyPolicyScreen()
                }
            }
        }
        if (showPopup.value) {
            MessagePopup(
                strRes = errorMsg.value ?: R.string.exception,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
                    .align(Alignment.Center)
                    .padding(24.dp)
            ) {
                showPopup.setFalse()
            }
        }
    }
}

private fun makeTitle(destination: NavDestination): String {
    return if (destination.route == AuthenticationScreen.Login.route ||
        destination.route == AuthenticationScreen.SignUp.route
    ) destination.route ?: "" else ""
}

sealed class AuthenticationScreen(val route: String) {
    object Authentication : AuthenticationScreen(route = "AUTHENTICATION")
    object Login : AuthenticationScreen(route = "Login")
    object SignUp : AuthenticationScreen(route = "Sign Up")
    object ResetPassword : AuthenticationScreen("RESET_PASSWORD")
}

data class TopBarInfo(
    val title: String = "",
    val isVisible: Boolean = false,
)