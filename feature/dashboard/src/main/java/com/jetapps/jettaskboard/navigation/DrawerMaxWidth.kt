package com.jetapps.jettaskboard.navigation

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.min

const val DrawerMaxWidth = 320f

class JtbDrawerShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(
            Rect(
                offset = Offset.Zero,
                size = Size(
                    width = min(size.width, DrawerMaxWidth * density.density),
                    height = size.height
                )
            )
        )
    }
}
