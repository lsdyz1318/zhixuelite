package com.zhixue.lite.core.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun Text(
    text: String,
    color: Color,
    style: TextStyle,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    androidx.compose.foundation.text.BasicText(
        text = text,
        modifier = modifier,
        style = style,
        maxLines = maxLines,
        overflow = overflow,
        color = { color }
    )
}

@Composable
fun Text(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    color: Color,
    style: TextStyle,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    androidx.compose.foundation.text.BasicText(
        text = text,
        modifier = modifier,
        style = style,
        maxLines = maxLines,
        overflow = overflow,
        color = { color }
    )
}