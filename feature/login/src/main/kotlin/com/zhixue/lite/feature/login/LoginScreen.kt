package com.zhixue.lite.feature.login

import android.content.Context
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.geetest.captcha.GTCaptcha4Client
import com.zhixue.lite.core.designsystem.component.Button
import com.zhixue.lite.core.designsystem.component.Divider
import com.zhixue.lite.core.designsystem.component.SecureTextField
import com.zhixue.lite.core.designsystem.component.Text
import com.zhixue.lite.core.designsystem.component.TextButton
import com.zhixue.lite.core.designsystem.component.TextField
import com.zhixue.lite.core.designsystem.component.Toast
import com.zhixue.lite.core.designsystem.theme.Theme

@Composable
internal fun LoginRoute(
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit,
    onForgetPasswordClick: () -> Unit,
    loginState: LoginState = rememberLoginState(),
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    Toast(
        message = viewModel.message,
        onMessageShown = viewModel::onMessageShown
    )

    LoginScreen(
        loginState = loginState,
        onLoginClick = {
            focusManager.clearFocus()
            captchaDialog(context) { captcha ->
                viewModel.login(
                    username = loginState.usernameText,
                    password = loginState.passwordText,
                    captcha = captcha,
                    onSuccess = onLoginSuccess
                )
            }
        },
        onRegisterClick = onRegisterClick,
        onForgetPasswordClick = onForgetPasswordClick
    )
}

@Composable
internal fun LoginScreen(
    loginState: LoginState,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onForgetPasswordClick: () -> Unit
) {
    val isFormsValid by loginState.isFormsValid.collectAsStateWithLifecycle()

    BoxWithConstraints {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = this@BoxWithConstraints.maxHeight)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(this@BoxWithConstraints.maxHeight * 0.1f))
            LoginHeader(modifier = Modifier.padding(horizontal = 32.dp))
            Spacer(modifier = Modifier.height(64.dp))
            LoginForms(
                username = loginState.username,
                password = loginState.password,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextButton(
                onClick = onForgetPasswordClick,
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Text(
                    text = stringResource(R.string.login_navigate_to_forget_password),
                    color = Theme.colorScheme.onBackgroundVariant,
                    style = Theme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                enabled = isFormsValid,
                shape = Theme.shapes.large,
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.login_submit_forms),
                    color = if (isFormsValid) Theme.colorScheme.onPrimary else Theme.colorScheme.onBackgroundVariant,
                    style = Theme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.weight(1f))
            LoginFooter(
                onRegisterClick = onRegisterClick,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
internal fun LoginHeader(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.login_title),
            color = Theme.colorScheme.onBackground,
            style = Theme.typography.headline
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .width(32.dp)
                .height(4.dp)
                .background(Theme.colorScheme.primary, Theme.shapes.small)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.login_subtitle),
            color = Theme.colorScheme.onBackground,
            style = Theme.typography.titleSmall.copy(fontWeight = FontWeight.Light)
        )
    }
}

@Composable
internal fun LoginForms(
    username: TextFieldState,
    password: TextFieldState,
    modifier: Modifier = Modifier
) {
    val isUsernameHintVisible by remember {
        derivedStateOf { username.text.isEmpty() }
    }
    val isPasswordHintVisible by remember {
        derivedStateOf { password.text.isEmpty() }
    }

    Column(modifier = modifier) {
        TextField(
            state = username,
            textColor = Theme.colorScheme.onBackground,
            style = Theme.typography.bodyLarge.copy(fontWeight = FontWeight.Light),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            cursorBrush = SolidColor(Theme.colorScheme.onBackgroundVariant),
            decorator = { innerTextField ->
                LoginTextFieldDecorator(
                    hintText = stringResource(R.string.login_username_hint),
                    isHintVisible = isUsernameHintVisible,
                    innerTextField = innerTextField
                )
            }
        )
        Spacer(modifier = Modifier.height(48.dp))
        SecureTextField(
            state = password,
            textColor = Theme.colorScheme.onBackground,
            style = Theme.typography.bodyLarge.copy(fontWeight = FontWeight.Light),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            cursorBrush = SolidColor(Theme.colorScheme.onBackgroundVariant),
            decorator = { innerTextField ->
                LoginTextFieldDecorator(
                    hintText = stringResource(R.string.login_password_hint),
                    isHintVisible = isPasswordHintVisible,
                    innerTextField = innerTextField
                )
            }
        )
    }
}

@Composable
internal fun LoginFooter(
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        TextButton(onClick = onRegisterClick) {
            Text(
                text = stringResource(R.string.login_navigate_to_register),
                color = Theme.colorScheme.primary,
                style = Theme.typography.bodyMedium
            )
        }
    }
}

@Composable
internal fun LoginTextFieldDecorator(
    hintText: String,
    isHintVisible: Boolean,
    innerTextField: @Composable () -> Unit
) {
    Column {
        Box {
            androidx.compose.animation.AnimatedVisibility(
                visible = isHintVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    text = hintText,
                    color = Theme.colorScheme.onBackgroundVariant,
                    style = Theme.typography.bodyLarge.copy(fontWeight = FontWeight.Light)
                )
            }
            innerTextField()
        }
        Spacer(modifier = Modifier.height(12.dp))
        Divider()
    }
}

internal fun captchaDialog(context: Context, onVerifySuccess: (String) -> Unit) {
    GTCaptcha4Client.getClient(context)
        .init("a6474422e78e5bb048082ec77d141068")
        .addOnSuccessListener { status, captcha ->
            if (status) {
                onVerifySuccess(captcha)
            }
        }
        .addOnFailureListener {}
        .verifyWithCaptcha()
}