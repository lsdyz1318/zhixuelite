package com.zhixue.lite.core.designsystem.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun ZhixueLiteTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) darkColorScheme else lightColorScheme

    CompositionLocalProvider(
        LocalIndication provides RippleIndication,
        LocalColorScheme provides colorScheme,
        content = content
    )
}

object Theme {
    val colorScheme: ColorScheme
        @Composable get() = LocalColorScheme.current
    val shapes: Shapes
        @Composable get() = LocalShapes.current
    val typography: Typography
        @Composable get() = LocalTypography.current
}