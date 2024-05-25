package com.zhixue.lite.core.designsystem.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

class Typography(
    val headline: TextStyle = TextStyle(
        fontSize = 32.sp
    ),
    val titleLarge: TextStyle = TextStyle(
        fontSize = 22.sp
    ),
    val titleMedium: TextStyle = TextStyle(
        fontSize = 16.sp
    ),
    val titleSmall: TextStyle = TextStyle(
        fontSize = 14.sp
    ),
    val bodyLarge: TextStyle = TextStyle(
        fontSize = 16.sp
    ),
    val bodyMedium: TextStyle = TextStyle(
        fontSize = 14.sp
    ),
    val bodySmall: TextStyle = TextStyle(
        fontSize = 12.sp
    )
)

internal val LocalTypography: ProvidableCompositionLocal<Typography> =
    staticCompositionLocalOf { Typography() }