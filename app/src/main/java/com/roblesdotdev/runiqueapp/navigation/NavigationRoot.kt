package com.roblesdotdev.runiqueapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.roblesdotdev.auth.presentation.intro.IntroScreenRoot
import com.roblesdotdev.auth.presentation.login.LoginScreenRoot
import com.roblesdotdev.auth.presentation.register.RegisterScreenRoot
import com.roblesdotdev.run.presentation.active_run.ActiveRunScreenRoot
import com.roblesdotdev.run.presentation.active_run.service.ActiveRunService
import com.roblesdotdev.run.presentation.run_overview.RunOverviewScreenRoot
import com.roblesdotdev.runiqueapp.MainActivity

@Composable
fun NavigationRoot(
    navController: NavHostController,
    isLoggedIn: Boolean,
) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) "run" else "auth"
    ) {
        authGraph(navController)
        runGraph(navController)
    }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation(
        startDestination = "intro",
        route = "auth",
    ) {
        composable(route = "intro") {
            IntroScreenRoot(
                onSignInClick = {
                    navController.navigate("login")
                },
                onSignUpClick = {
                    navController.navigate("register")
                }
            )
        }
        composable(route = "register") {
            RegisterScreenRoot(
                onSignInClick = {
                    navController.navigate("login") {
                        popUpTo("register") {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onSuccessfulRegistration = {
                    navController.navigate("login")
                }
            )
        }

        composable(route = "login") {
            LoginScreenRoot(
                onLoginSuccess = {
                    navController.navigate("run") {
                        popUpTo("auth") {
                            inclusive = true
                        }
                    }
                },
                onRegisterClick = {
                    navController.navigate("register") {
                        popUpTo("login") {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                }
            )
        }
    }
}

private fun NavGraphBuilder.runGraph(navController: NavHostController) {
    navigation(
        startDestination = "run_overview",
        route = "run"
    ) {
        composable("run_overview") {
            RunOverviewScreenRoot(
                onStartRunClick = {
                    navController.navigate("active_run")
                }
            )
        }

        composable(
            route = "active_run",
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "runique://active_run"
                }
            )
        ) {
            val context = LocalContext.current
            ActiveRunScreenRoot(
                onServiceToggle = { shouldServiceRun ->
                    if (shouldServiceRun) {
                        context.startService(
                            ActiveRunService.createStartIntent(
                                context = context,
                                activityClass = MainActivity::class.java,
                            )
                        )
                    } else {
                       context.startService(
                           ActiveRunService.createStopIntent(context = context)
                       )
                    }
                }
            )
        }
    }
}