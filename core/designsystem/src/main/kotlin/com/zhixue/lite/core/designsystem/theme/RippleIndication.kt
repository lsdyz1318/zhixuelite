package com.zhixue.lite.core.designsystem.theme

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.createRippleModifierNode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.unit.Dp

private val RippleAlpha: RippleAlpha =
    RippleAlpha(
        pressedAlpha = 0.10f,
        focusedAlpha = 0.12f,
        draggedAlpha = 0.08f,
        hoveredAlpha = 0.04f
    )

internal object RippleIndication : IndicationNodeFactory {

    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return DelegatingThemeAwareRippleNode(interactionSource)
    }

    override fun hashCode(): Int = -1

    override fun equals(other: Any?): Boolean = other === this
}

private class DelegatingThemeAwareRippleNode(
    interactionSource: InteractionSource
) : DelegatingNode(), CompositionLocalConsumerModifierNode {
    init {
        delegate(
            createRippleModifierNode(
                interactionSource = interactionSource,
                bounded = true,
                radius = Dp.Unspecified,
                color = { Color.Black },
                rippleAlpha = { RippleAlpha }
            )
        )
    }
}