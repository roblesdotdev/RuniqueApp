package com.roblesdotdev.auth.presentation.register

import com.roblesdotdev.core.presentation.ui.UiText

sealed interface RegisterEvent {
    data object RegistrationSuccess: RegisterEvent
    data class Failure(val error: UiText): RegisterEvent
}