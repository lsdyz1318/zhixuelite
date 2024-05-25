package com.zhixue.lite.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.zhixue.lite.core.designsystem.theme.Theme

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = Theme.shapes.small,
    background: Color = Theme.colorScheme.primary,
    disableBackground: Color = Theme.colorScheme.container,
    contentPadding: PaddingValues = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
    content: @Composable () -> Unit
) {
    ButtonImpl(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        contentPadding = contentPadding,
        background = background,
        disableBackground = disableBackground,
        content = content
    )
}

@Composable
fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = Theme.shapes.small,
    background: Color = Color.Transparent,
    disableBackground: Color = Color.Transparent,
    contentPadding: PaddingValues = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
    content: @Composable () -> Unit
) {
    ButtonImpl(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        contentPadding = contentPadding,
        background = background,
        disableBackground = disableBackground,
        content = content
    )
}

@Composable
private fun ButtonImpl(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    shape: Shape,
    background: Color,
    disableBackground: Color,
    contentPadding: PaddingValues,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(if (enabled) background else disableBackground)
            .clickable(enabled = enabled, onClick = onClick)
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}