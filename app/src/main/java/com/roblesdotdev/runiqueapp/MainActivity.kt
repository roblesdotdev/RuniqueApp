package com.roblesdotdev.runiqueapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.roblesdotdev.auth.presentation.intro.IntroScreenRoot
import com.roblesdotdev.core.presentation.designsystem.RuniqueAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RuniqueAppTheme {
                IntroScreenRoot(
                    onSignInClick = {},
                    onSignUpClick = {},
                )
            }
        }
    }
}
