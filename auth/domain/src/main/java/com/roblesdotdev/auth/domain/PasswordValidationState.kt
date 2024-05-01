package com.roblesdotdev.auth.domain

data class PasswordValidationState(
    val hasMinLength: Boolean = false,
    val hasNumber: Boolean = false,
    val hasLowerCaseCharacter: Boolean = false,
    val hasUpperCaseCharacter: Boolean = false,
) {
    val isValidPassword: Boolean
        get() = hasMinLength && hasNumber && hasLowerCaseCharacter && hasUpperCaseCharacter

    companion object {
        const val MIN_PASSWORD_LENGTH = 9
    }
}