@file:Suppress("DEPRECATION")

package com.zhixue.lite.core.designsystem.modifier

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.zhixue.lite.core.designsystem.theme.Theme

@Composable
fun Modifier.themePlaceholder(visible: Boolean): Modifier = placeholder(
    visible = visible,
    color = Theme.colorScheme.container,
    shape = Theme.shapes.medium,
    highlight = PlaceholderHighlight.shimmer(Theme.colorScheme.background)
)