package com.zhixue.lite.core.designsystem.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldDecorator
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle

@Composable
fun TextField(
    state: TextFieldState,
    textColor: Color,
    style: TextStyle,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.SingleLine,
    cursorBrush: Brush = SolidColor(Color.Black),
    decorator: TextFieldDecorator
) {
    androidx.compose.foundation.text.BasicTextField(
        state = state,
        modifier = modifier,
        textStyle = style.merge(color = textColor),
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
        lineLimits = lineLimits,
        cursorBrush = cursorBrush,
        decorator = decorator
    )
}

@Composable
fun SecureTextField(
    state: TextFieldState,
    textColor: Color,
    style: TextStyle,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    cursorBrush: Brush = SolidColor(Color.Black),
    decorator: TextFieldDecorator
) {
    androidx.compose.foundation.text.BasicSecureTextField(
        state = state,
        modifier = modifier,
        textStyle = style.merge(color = textColor),
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
        cursorBrush = cursorBrush,
        decorator = decorator
    )
}