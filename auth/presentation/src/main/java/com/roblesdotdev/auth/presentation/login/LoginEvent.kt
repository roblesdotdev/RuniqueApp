package com.roblesdotdev.auth.presentation.login

import com.roblesdotdev.core.presentation.ui.UiText

sealed interface LoginEvent {
    data class Error(val error: UiText): LoginEvent

    data object LoginSuccess: LoginEvent
}