package com.roblesdotdev.runiqueapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.roblesdotdev.core.presentation.designsystem.RuniqueAppTheme
import com.roblesdotdev.runiqueapp.navigation.NavigationRoot
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.state.isCheckingAuth
            }
        }
        setContent {
            RuniqueAppTheme {
                val navController = rememberNavController()
                if (!viewModel.state.isCheckingAuth) {
                    NavigationRoot(
                        navController = navController,
                        isLoggedIn = viewModel.state.isLoggedIn
                    )
                }
            }
        }
    }
}
