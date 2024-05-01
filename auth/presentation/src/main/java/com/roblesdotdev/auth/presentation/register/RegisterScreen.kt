package com.roblesdotdev.auth.presentation.register

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roblesdotdev.auth.domain.PasswordValidationState
import com.roblesdotdev.auth.domain.UserDataValidator
import com.roblesdotdev.auth.presentation.R
import com.roblesdotdev.core.presentation.designsystem.CheckIcon
import com.roblesdotdev.core.presentation.designsystem.CrossIcon
import com.roblesdotdev.core.presentation.designsystem.EmailIcon
import com.roblesdotdev.core.presentation.designsystem.Poppins
import com.roblesdotdev.core.presentation.designsystem.RuniqueAppTheme
import com.roblesdotdev.core.presentation.designsystem.RuniqueDarkRed
import com.roblesdotdev.core.presentation.designsystem.RuniqueGray
import com.roblesdotdev.core.presentation.designsystem.RuniqueGreen
import com.roblesdotdev.core.presentation.designsystem.components.GradientBackground
import com.roblesdotdev.core.presentation.designsystem.components.RuniqueActionButton
import com.roblesdotdev.core.presentation.designsystem.components.RuniquePasswordTextField
import com.roblesdotdev.core.presentation.designsystem.components.RuniqueTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreenRoot(
    viewModel: RegisterViewModel = koinViewModel(),
) {
    RegisterScreen(state = viewModel.state, onAction = viewModel::onAction)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit,
) {
    GradientBackground {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 32.dp)
                .padding(top = 16.dp),
        ) {
            Text(
                text = "Create Account",
                style = MaterialTheme.typography.headlineMedium
            )
            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontFamily = Poppins,
                        color = RuniqueGray,
                    )
                ) {
                    append(stringResource(R.string.already_have_an_account) + " ")
                    pushStringAnnotation(
                        tag = "clickable_text",
                        annotation = stringResource(R.string.login)
                    )
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = Poppins
                        )
                    ) {
                        append(stringResource(id = R.string.login))
                    }
                }
            }
            ClickableText(text = annotatedString, onClick = { offset ->
                annotatedString.getStringAnnotations(
                    tag = "clickable_text",
                    start = offset,
                    end = offset,
                ).firstOrNull()?.let {
                    onAction(RegisterAction.OnLoginClick)
                }
            })
            Spacer(modifier = Modifier.height(48.dp))
            RuniqueTextField(
                state = state.email,
                startIcon = EmailIcon,
                endIcon = if (state.isEmailValid) {
                    CheckIcon
                } else {
                    null
                },
                hint = stringResource(R.string.demo_email),
                title = stringResource(R.string.email),
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Email,
                additionalInfo = stringResource(R.string.info_valid_email)
            )
            Spacer(modifier = Modifier.height(16.dp))
            RuniquePasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                state = state.password,
                hint = stringResource(id = R.string.password),
                title = stringResource(
                    R.string.password
                ),
                isVisible = true,
                onChangeVisibility = {
                    onAction(RegisterAction.OnTogglePasswordVisibilityClick)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordRequirementList(state = state.passwordValidationState)
            Spacer(modifier = Modifier.height(32.dp))
            RuniqueActionButton(
                text = stringResource(R.string.register),
                isLoading = state.isRegistering,
                enabled = state.canRegister,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onAction(RegisterAction.OnRegisterClick)
                })
        }
    }
}

@Composable
fun PasswordRequirementList(state: PasswordValidationState) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        PasswordRequirement(
            text = stringResource(
                R.string.at_least_x_characters,
                UserDataValidator.MIN_PASSWORD_LENGTH,
            ),
            isValid = state.hasMinLength,
        )
        PasswordRequirement(
            text = stringResource(R.string.at_least_one_number),
            isValid = state.hasNumber,
        )
        PasswordRequirement(
            text = stringResource(R.string.contains_lowercase_char),
            isValid = state.hasLowerCaseCharacter,
        )
        PasswordRequirement(
            text = stringResource(R.string.contains_uppercase_char),
            isValid = state.hasUpperCaseCharacter,
        )
    }
}

@Composable
fun PasswordRequirement(
    modifier: Modifier = Modifier,
    text: String,
    isValid: Boolean,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = if (isValid) CheckIcon else CrossIcon,
            contentDescription = null,
            tint = if (isValid) RuniqueGreen else RuniqueDarkRed
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp,
        )
    }
}

@Suppress("OPT_IN_USAGE_FUTURE_ERROR")
@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    RuniqueAppTheme {
        RegisterScreen(
            state = RegisterState(),
            onAction = {}
        )
    }
}