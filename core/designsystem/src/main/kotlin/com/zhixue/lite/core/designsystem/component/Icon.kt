package com.zhixue.lite.core.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun Icon(
    painter: Painter,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    val colorFilter = remember(tint) {
        if (tint == Color.Unspecified) null else ColorFilter.tint(tint)
    }
    androidx.compose.foundation.Image(
        painter = painter,
        modifier = modifier,
        contentDescription = null,
        colorFilter = colorFilter
    )
}