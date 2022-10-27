package com.jetapps.jettaskboard.loadingcomponents

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun TrelloNotificationAnimationIcon(
    modifier: Modifier = Modifier,
    notificationCounts: Int = 0,
    backgroundColor: Color = Color(0xFF3f3f41),
    canvasSize: Dp = 28.dp,
    iconSize : Dp = 24.dp
) {

    val roundCircleWaves = listOf(
        remember { Animatable(0f) },
        remember { Animatable(0f) },
    )

    val infiniteWaveAnimation = infiniteRepeatable<Float>(
        repeatMode = RepeatMode.Restart,
        animation = tween(
            durationMillis = 4000,
            easing = FastOutLinearInEasing
        )
    )

    if (notificationCounts > 0) {
        roundCircleWaves.forEachIndexed { index, animatable ->
            LaunchedEffect(key1 = animatable, block = {
                delay(index * 1000L)
                animatable.animateTo(
                    targetValue = 1f,
                    animationSpec = infiniteWaveAnimation
                )
            })
        }
    }

    Box(
        modifier = modifier.size(canvasSize),
        contentAlignment = Alignment.Center
    ) {
        // Waves
        roundCircleWaves.forEach { animatable ->
            Box(
                Modifier
                    .size(canvasSize.div(2))
                    .align(Alignment.Center)
                    .graphicsLayer {
                        scaleX = animatable.value * 2 + 1
                        scaleY = animatable.value * 2 + 1
                        alpha = 1 - animatable.value
                    },
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(color = Color.White, shape = CircleShape)
                )
            }
        }

        // Notification icon
        Box(
            Modifier
                .size(canvasSize)
                .align(Alignment.Center)
                .background(color = backgroundColor, shape = CircleShape)
                .padding(4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                "MainAppBar Notification Icon",
                modifier = Modifier
                    .size(iconSize)
                    .align(Alignment.Center)
            )

            Box(
                Modifier
                    .size(8.dp)
                    .align(Alignment.TopEnd)
                    .background(color = Color.Yellow, shape = CircleShape)
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = if (notificationCounts >= 10) "9+" else notificationCounts.toString(),
                    color = Color.Black,
                    fontSize = 6.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
