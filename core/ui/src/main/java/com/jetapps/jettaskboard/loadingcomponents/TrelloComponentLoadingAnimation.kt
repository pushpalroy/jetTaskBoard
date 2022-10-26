package com.jetapps.jettaskboard.loadingcomponents

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

@Composable
fun TrelloComponentLoadingAnimation(
    modifier: Modifier = Modifier,
    widthOfCanvas: Dp = 30.dp,
    backgroundColor: Color = MaterialTheme.colors.onSurface,
    fillColor: Color = MaterialTheme.colors.onSurface
) {
    val widthOfLoadingRectangle: Dp = widthOfCanvas.div(10)

    val heightOfRectangle: Dp = widthOfCanvas.div(2)

    var animatedRectangleOneHeight by remember {
        mutableStateOf(0.dp)
    }

    var animatedRectangleTwoHeight by remember {
        mutableStateOf(0.dp)
    }

    var animatedRectangleThreeHeight by remember {
        mutableStateOf(0.dp)
    }

    var animatedRectangleFourHeight by remember {
        mutableStateOf(0.dp)
    }

    LaunchedEffect(key1 = Unit, block = {
        while (true) {
            val firstRectangleJob = launch {
                animate(
                    initialValue = 0f,
                    targetValue = heightOfRectangle.value,
                    initialVelocity = 0f,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearEasing
                    ),
                    block = { value, _ ->
                        animatedRectangleOneHeight = value.dp
                    }
                )

                animate(
                    initialValue = widthOfCanvas.value.div(3f),
                    targetValue = 0f,
                    initialVelocity = 0f,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearOutSlowInEasing
                    ),
                    block = { value, _ ->
                        animatedRectangleOneHeight = value.dp
                    }
                )
            }

            val secondRectangleJob = launch {
                delay(200)
                animate(
                    initialValue = 0f,
                    targetValue = heightOfRectangle.value,
                    initialVelocity = 0f,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearOutSlowInEasing
                    ),
                    block = { value, _ ->
                        animatedRectangleTwoHeight = value.dp
                    }
                )

                animate(
                    initialValue = widthOfCanvas.value.div(3f),
                    targetValue = 0f,
                    initialVelocity = 0f,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearOutSlowInEasing
                    ),
                    block = { value, _ ->
                        animatedRectangleTwoHeight = value.dp
                    }
                )
            }

            val thirdRectangleJob = launch {
                delay(400)
                animate(
                    initialValue = 0f,
                    targetValue = heightOfRectangle.value,
                    initialVelocity = 0f,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearOutSlowInEasing
                    ),
                    block = { value, _ ->
                        animatedRectangleThreeHeight = value.dp
                    }
                )

                animate(
                    initialValue = widthOfCanvas.value.div(3f),
                    targetValue = 0f,
                    initialVelocity = 0f,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearOutSlowInEasing
                    ),
                    block = { value, _ ->
                        animatedRectangleThreeHeight = value.dp
                    }
                )
            }

            val fourthRectangleJob = launch {
                delay(600)
                animate(
                    initialValue = 0f,
                    targetValue = heightOfRectangle.value,
                    initialVelocity = 0f,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearOutSlowInEasing
                    ),
                    block = { value, _ ->
                        animatedRectangleFourHeight = value.dp
                    }
                )

                animate(
                    initialValue = widthOfCanvas.value.div(3f),
                    targetValue = 0f,
                    initialVelocity = 0f,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearOutSlowInEasing
                    ),
                    block = { value, _ ->
                        animatedRectangleFourHeight = value.dp
                    }
                )
            }

            joinAll(
                firstRectangleJob,
                secondRectangleJob,
                thirdRectangleJob,
                fourthRectangleJob
            )
        }
    })

    Canvas(
        modifier = modifier,
    ) {
        // Bordered Rectangle
        drawRoundRect(
            color = backgroundColor,
            topLeft = Offset(
                x = -widthOfLoadingRectangle.toPx(),
                y = -widthOfLoadingRectangle.toPx()
            ),
            size = Size(
                width = widthOfCanvas.toPx().plus(widthOfLoadingRectangle.toPx().times(2)),
                height = widthOfCanvas.toPx().div(2).plus(widthOfLoadingRectangle.toPx().times(2))
            ),
            style = Stroke(
                width = 2.dp.toPx()
            ),
            cornerRadius = CornerRadius(
                x = 2f,
                y = 2f
            )
        )

        drawRoundRect(
            color = backgroundColor,
            style = Stroke(
                width = 2.dp.toPx()
            ),
            topLeft = Offset.Zero
        )
        drawRect(
            color = fillColor,
            topLeft = Offset(
                widthOfLoadingRectangle.toPx().times(1),
                0f
            ),
            size = Size(
                widthOfLoadingRectangle.toPx(),
                animatedRectangleOneHeight.toPx()
            )
        )

        drawRect(
            color = fillColor,
            topLeft = Offset(widthOfLoadingRectangle.toPx().times(3), 0f),
            size = Size(
                widthOfLoadingRectangle.toPx(),
                animatedRectangleTwoHeight.toPx()
            )
        )

        drawRect(
            color = fillColor,
            topLeft = Offset(widthOfLoadingRectangle.toPx().times(5), 0f),
            size = Size(
                widthOfLoadingRectangle.toPx(),
                animatedRectangleThreeHeight.toPx()
            )
        )

        drawRect(
            color = fillColor,
            topLeft = Offset(widthOfLoadingRectangle.toPx().times(7), 0f),
            size = Size(
                widthOfLoadingRectangle.toPx(),
                animatedRectangleFourHeight.toPx()
            )
        )
    }
}