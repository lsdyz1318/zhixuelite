package com.zhixue.lite.core.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Immutable
class Shapes(
    val small: Shape = RoundedCornerShape(6.dp),
    val medium: Shape = RoundedCornerShape(16.dp),
    val large: Shape = RoundedCornerShape(28.dp)
)

internal val LocalShapes: ProvidableCompositionLocal<Shapes> =
    staticCompositionLocalOf { Shapes() }