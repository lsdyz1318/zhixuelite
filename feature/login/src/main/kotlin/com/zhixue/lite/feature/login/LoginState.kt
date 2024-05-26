package com.zhixue.lite.feature.login

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

@Composable
internal fun rememberLoginState(
    username: TextFieldState = rememberTextFieldState(),
    password: TextFieldState = rememberTextFieldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): LoginState {
    return remember(
        username,
        password,
        coroutineScope
    ) {
        LoginState(
            username = username,
            password = password,
            coroutineScope = coroutineScope
        )
    }
}

internal class LoginState(
    val username: TextFieldState,
    val password: TextFieldState,
    coroutineScope: CoroutineScope
) {
    val usernameText: String
        get() = username.text.toString()

    val passwordText: String
        get() = password.text.toString()

    val isFormsValid: StateFlow<Boolean> =
        snapshotFlow {
            username.text.length >= 6 && password.text.length >= 6
        }.stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )
}