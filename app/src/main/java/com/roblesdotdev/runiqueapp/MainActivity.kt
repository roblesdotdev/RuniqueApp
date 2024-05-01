package com.roblesdotdev.runiqueapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.roblesdotdev.auth.presentation.intro.IntroScreenRoot
import com.roblesdotdev.auth.presentation.register.RegisterScreen
import com.roblesdotdev.auth.presentation.register.RegisterState
import com.roblesdotdev.core.presentation.designsystem.RuniqueAppTheme

@Suppress("OPT_IN_USAGE_FUTURE_ERROR")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RuniqueAppTheme {
                RegisterScreen(
                    onAction = {},
                    state = RegisterState()
                )
            }
        }
    }
}
