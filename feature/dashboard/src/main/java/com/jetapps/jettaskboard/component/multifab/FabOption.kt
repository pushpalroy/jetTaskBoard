package com.jetapps.jettaskboard.component.multifab

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Immutable
interface FabOption {
    @Stable
    val iconTint: Color

    @Stable
    val backgroundTint: Color

    @Stable
    val showLabels: Boolean
}

private class FabOptionImpl(
    override val iconTint: Color,
    override val backgroundTint: Color,
    override val showLabels: Boolean
) : FabOption

/**
 * Affects all fabs including sub fabs.
 */
@SuppressLint("ComposableNaming")
@Composable
fun FabOption(
    backgroundTint: Color = MaterialTheme.colors.secondary,
    iconTint: Color = contentColorFor(backgroundTint),
    showLabels: Boolean = false
): FabOption =
    FabOptionImpl(iconTint = iconTint, backgroundTint = backgroundTint, showLabels = showLabels)
