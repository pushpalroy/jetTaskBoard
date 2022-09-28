package com.jetapps.jettaskboard.component

import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.createBitmap
import androidx.core.view.ViewCompat.animate
import com.jetapps.jettaskboard.feature.dashboard.R

@Composable
fun ExpandableFloatActionButton(
    fabIcon: ImageVector = Icons.Default.Add,
    isExpanded: Boolean,
    onStateChanged: (isExpanded: Boolean) -> Unit,
    navigateToBoardScreen: (String) -> Unit,
    navigateToCardScreen: (String) -> Unit
) {
    val transition: Transition<Boolean> =
        updateTransition(targetState = isExpanded, label = "update_transition")

    val scale: Float by transition.animateFloat(label = "update_scale") { state ->
        if (state) 56f else 0f
    }

    val alpha: Float by transition.animateFloat(label = "update_alpha") { state ->
        if (state) 1f else 0f
    }

    val shadow : Dp by transition.animateDp(label = "update_shadow_effect") { state ->
        if (state) 2.dp else 0.dp
    }

    val rotation: Float by transition.animateFloat(label = "rotating_icon") { state ->
        if (state) 45f else 0f
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isExpanded) {
            MiniFloatingActionButton(
                label = "Board",
                image = ImageBitmap.imageResource(id = R.drawable.dashboard_icon),
                alphaValue = alpha,
                scaleValue = scale,
                shadow = shadow
            ) {
                navigateToBoardScreen("Project One")
            }

            MiniFloatingActionButton(
                label = "Card",
                image = ImageBitmap.imageResource(id = R.drawable.card_icon),
                alphaValue = alpha,
                scaleValue = scale,
                shadow = shadow
            ) {
                navigateToCardScreen("Project One")
            }
        }

        FloatingActionButton(onClick = { onStateChanged(!isExpanded) }) {
            Icon(
                imageVector = fabIcon,
                contentDescription = "FAB Icon",
                modifier = Modifier.rotate(rotation),
            )
        }
    }
}