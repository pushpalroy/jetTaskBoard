package com.jetapps.jettaskboard.loadingcomponents

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

@Composable
fun TrelloLoadingAnimation(
    modifier: Modifier = Modifier,
    sizeOfCanvas: Dp = 200.dp,
    backgroundColor: Color = MaterialTheme.colors.surface,
    fillColor: Color = MaterialTheme.colors.onPrimary
) {
    var animatedLeftRectangleHeight by remember {
        mutableStateOf(0.dp)
    }

    var animatedRightRectangleHeight by remember {
        mutableStateOf(0.dp)
    }

    LaunchedEffect(key1 = Unit, block = {
        while (true) {
            val leftAnimationJob = launch {
                animate(
                    initialValue = 0f,
                    targetValue = sizeOfCanvas.value.div(3f),
                    initialVelocity = 0f,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearOutSlowInEasing
                    ),
                    block = { value, _ ->
                        animatedLeftRectangleHeight = value.dp
                    }
                )

                animate(
                    initialValue = sizeOfCanvas.value.div(3f),
                    targetValue = 0f,
                    initialVelocity = 0f,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearOutSlowInEasing
                    ),
                    block = { value, _ ->
                        animatedLeftRectangleHeight = value.dp
                    }
                )
            }

            val rightAnimationJob = launch {
                animate(
                    initialValue = sizeOfCanvas.value.div(2f),
                    targetValue = 0f,
                    initialVelocity = 0.5f,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearOutSlowInEasing
                    ),
                    block = { value, _ ->
                        animatedRightRectangleHeight = value.dp
                    }
                )

                animate(
                    initialValue = 0f,
                    targetValue = sizeOfCanvas.value.div(2f),
                    initialVelocity = 0.5f,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearOutSlowInEasing
                    ),
                    block = { value, _ ->
                        animatedRightRectangleHeight = value.dp
                    }
                )
            }

            joinAll(rightAnimationJob, leftAnimationJob)
        }
    })

    Canvas(
        modifier = modifier
            .padding(8.dp)
            .size(sizeOfCanvas)
    ) {
        val borderStrokeWidth = sizeOfCanvas.toPx().div(10)
        val innerRectangleWidth = sizeOfCanvas.toPx().div(2)
            .minus(borderStrokeWidth.times(2))

        val innerRectangleYOffSetValue = sizeOfCanvas.toPx().minus(borderStrokeWidth)

        // Left Rectangle Border
        drawRoundRect(
            color = backgroundColor,
            topLeft = Offset.Zero,
            size = Size(
                width = sizeOfCanvas.toPx().div(2),
                height = sizeOfCanvas.toPx()
            ),
            style = Stroke(
                width = borderStrokeWidth
            ),
            cornerRadius = CornerRadius(x = 2f, y = 2f)
        )

        // Left Inner Rectangle for Animation
        drawRect(
            color = fillColor,
            topLeft = Offset(
                x = borderStrokeWidth,
                y = innerRectangleYOffSetValue
            ),
            size = Size(
                width = innerRectangleWidth,
                height = -animatedLeftRectangleHeight.toPx()
            )
        )

        // Right Rectangle Border
        drawRoundRect(
            color = backgroundColor,
            topLeft = Offset(
                x = sizeOfCanvas.toPx().div(2),
                y = 0f
            ),
            size = Size(
                width = sizeOfCanvas.toPx().div(2),
                height = sizeOfCanvas.toPx()
            ),
            style = Stroke(
                width = borderStrokeWidth
            ),
            cornerRadius = CornerRadius(x = 2f, y = 2f)
        )

        // Right Inner Rectangle for Animation
        drawRect(
            color = fillColor,
            topLeft = Offset(
                x = sizeOfCanvas.toPx().div(2).plus(borderStrokeWidth),
                y = innerRectangleYOffSetValue
            ),
            size = Size(
                width = innerRectangleWidth,
                height = -animatedRightRectangleHeight.toPx()
            )
        )
    }
}