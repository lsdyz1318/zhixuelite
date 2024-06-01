package com.zhixue.lite.core.designsystem.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zhixue.lite.core.designsystem.theme.Theme

@Composable
fun CircularChart(
    value: Float,
    modifier: Modifier,
    thickness: Dp = 8.dp,
    animationDuration: Int = 400,
    color: Color = Theme.colorScheme.primary,
    backgroundColor: Color = Theme.colorScheme.container
) {
    val sweepAngle = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        sweepAngle.animateTo(-360 * value, tween(animationDuration))
    }

    Canvas(modifier = modifier) {
        drawCircle(
            color = backgroundColor,
            style = Stroke(width = thickness.toPx(), cap = StrokeCap.Butt)
        )
        drawArc(
            color = color,
            startAngle = -90f,
            sweepAngle = sweepAngle.value,
            useCenter = false,
            style = Stroke(width = thickness.toPx(), cap = StrokeCap.Round)
        )
    }
}