package com.zhixue.lite.core.designsystem.theme

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.invalidateDraw
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal object Indication : IndicationNodeFactory {

    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return IndicationInstance(interactionSource)
    }

    override fun hashCode(): Int = -1

    override fun equals(other: Any?): Boolean = other === this
}

private class IndicationInstance(
    private val interactionSource: InteractionSource
) : Modifier.Node(), DrawModifierNode {

    private var isPressed: Boolean = false

    override fun onAttach() {
        coroutineScope.launch {
            interactionSource.interactions.collectLatest { interaction ->
                var pressed = false
                // 检测按压状态
                when (interaction) {
                    is PressInteraction.Press -> pressed = true
                    is PressInteraction.Release -> pressed = false
                    is PressInteraction.Cancel -> pressed = false
                }
                // 如果按压状态发生变化，则重新绘制
                if (isPressed != pressed) {
                    isPressed = pressed
                    invalidateDraw()
                }
            }
        }
    }

    override fun ContentDrawScope.draw() {
        drawContent()
        if (isPressed) {
            drawRect(color = Color.Black.copy(alpha = 0.1f))
        }
    }
}