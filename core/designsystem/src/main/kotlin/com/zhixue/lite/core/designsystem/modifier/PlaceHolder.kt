@file:Suppress("DEPRECATION")

package com.zhixue.lite.core.designsystem.modifier

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.isSpecified
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

fun Modifier.placeholder(
    visible: Boolean,
    color: Color,
    highlightColor: Color = Color.Unspecified,
    shape: Shape = RectangleShape
): Modifier = placeholder(
    visible = visible,
    color = color,
    shape = shape,
    highlight = highlightColor.takeIf { it.isSpecified }?.let(PlaceholderHighlight::shimmer)
)