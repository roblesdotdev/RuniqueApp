@file:OptIn(ExperimentalFoundationApi::class)

package com.roblesdotdev.auth.presentation.login

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roblesdotdev.auth.presentation.R
import com.roblesdotdev.core.presentation.designsystem.EmailIcon
import com.roblesdotdev.core.presentation.designsystem.Poppins
import com.roblesdotdev.core.presentation.designsystem.RuniqueAppTheme
import com.roblesdotdev.core.presentation.designsystem.RuniqueGray
import com.roblesdotdev.core.presentation.designsystem.components.GradientBackground
import com.roblesdotdev.core.presentation.designsystem.components.RuniqueActionButton
import com.roblesdotdev.core.presentation.designsystem.components.RuniquePasswordTextField
import com.roblesdotdev.core.presentation.designsystem.components.RuniqueTextField
import com.roblesdotdev.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is LoginEvent.Error -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }

            LoginEvent.LoginSuccess -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    R.string.youre_logged_in,
                    Toast.LENGTH_LONG
                ).show()
                onLoginSuccess()
            }
        }
    }

    LoginScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                is LoginAction.OnRegisterClick -> onRegisterClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
) {
    GradientBackground(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 32.dp)
                .padding(top = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.hi_there),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                text = stringResource(R.string.welcome_text),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.height(48.dp))
            RuniqueTextField(
                state = state.email,
                startIcon = EmailIcon,
                endIcon = null,
                hint = stringResource(id = R.string.demo_email),
                title = stringResource(id = R.string.email),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            RuniquePasswordTextField(
                state = state.password,
                hint = stringResource(id = R.string.password),
                title = stringResource(
                    id = R.string.password
                ),
                isVisible = state.isPasswordVisible,
                onChangeVisibility = {
                    onAction(LoginAction.OnTogglePasswordVisibility)
                },
                imeAction = ImeAction.Done,
            )
            Spacer(modifier = Modifier.height(32.dp))
            RuniqueActionButton(
                text = stringResource(id = R.string.login),
                isLoading = state.isLoggingIn,
                enabled = state.canLogin && !state.isLoggingIn,
                onClick = {
                    onAction(LoginAction.OnLoginClick)
                },
                modifier = Modifier.fillMaxWidth()
            )
            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontFamily = Poppins,
                        color = RuniqueGray,
                    )
                ) {
                    append(stringResource(R.string.dont_haven_an_account) + " ")
                    pushStringAnnotation(
                        tag = "clickable_text",
                        annotation = stringResource(R.string.sign_up)
                    )
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = Poppins
                        )
                    ) {
                        append(stringResource(id = R.string.sign_up))
                    }
                }
            }
            Box(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.BottomCenter
            ) {
                ClickableText(
                    text = annotatedString,
                    onClick = { offset ->
                        annotatedString.getStringAnnotations(
                            tag = "clickable_text",
                            start = offset,
                            end = offset,
                        ).firstOrNull()?.let {
                            onAction(LoginAction.OnRegisterClick)
                        }
                    })
            }
        }
    }

}

@Preview
@Composable
private fun LoginScreenPreview() {
    RuniqueAppTheme {
        LoginScreen(state = LoginState(), onAction = {})
    }
}