package com.zhixue.lite.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
class ColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val container: Color,
    val background: Color,
    val onBackground: Color,
    val onBackgroundVariant: Color,
    val outline: Color
)

internal val lightColorScheme: ColorScheme = ColorScheme(
    primary = Color(0xFF15D299),
    onPrimary = Color(0xFFFFFFFF),
    container = Color(0xFFF4F6F8),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF000000),
    onBackgroundVariant = Color(0xFFCCCCCC),
    outline = Color(0xFFF8F8F8)
)

internal val darkColorScheme: ColorScheme = ColorScheme(
    primary = Color.Unspecified,
    onPrimary = Color.Unspecified,
    container = Color.Unspecified,
    background = Color.Unspecified,
    onBackground = Color.Unspecified,
    onBackgroundVariant = Color.Unspecified,
    outline = Color.Unspecified
)

internal val LocalColorScheme: ProvidableCompositionLocal<ColorScheme> =
    staticCompositionLocalOf { lightColorScheme }