package com.jetapps.jettaskboard.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MiniFloatingActionButton(
    label: String,
    image: ImageBitmap,
    alphaValue: Float,
    shadow: Dp,
    scaleValue: Float,
    onMiniFloatingActionButtonClicked: () -> Unit
) {
    val buttonPrimaryColor = MaterialTheme.colors.primary
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 12.dp)
    ) {
        Text(
            label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .background(color = MaterialTheme.colors.surface)
                .padding(start = 6.dp, end = 6.dp, top = 4.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Canvas(
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    onMiniFloatingActionButtonClicked()
                },
            onDraw = {
                drawCircle(
                    color = buttonPrimaryColor,
                    scaleValue
                )
                drawImage(
                    image = image,
                    topLeft = Offset(
                        (this.center.x) - (image.width / 2),
                        (this.center.y) - (image.width / 2)
                    ),
                    alpha = alphaValue
                )
            }
        )
    }

    Spacer(modifier = Modifier.height(24.dp))
}