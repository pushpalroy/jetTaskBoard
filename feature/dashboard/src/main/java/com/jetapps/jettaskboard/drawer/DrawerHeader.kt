package com.jetapps.jettaskboard.drawer

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetapps.jettaskboard.components.CoilAsyncImage

// Todo(Niket): Fix the Drawer toggle button
@Composable
fun DrawerHeader(
    onDrawerHeaderToggled: () -> Unit,
    modifier: Modifier,
    isDrawerContentExpanded: Boolean = false
) {
    val rotateArrowState by animateFloatAsState(
        targetValue = if (isDrawerContentExpanded) 180f else 0f,
        tween(350), label = "rotate_arrow_state"
    )

    Box(
        modifier = modifier
            .background(color = MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            CoilAsyncImage(imageShape = CircleShape, imageSize = 52.dp)

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Trello Org",
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 16.sp)
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "@trello",
                style = TextStyle(fontSize = 14.sp)
            )

            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "board@trello.com",
                    modifier = Modifier.weight(1f),
                    style = TextStyle(fontSize = 14.sp)
                )
                IconButton(
                    onClick = { onDrawerHeaderToggled() }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        contentDescription = "Drop Down",
                        modifier = Modifier
                            .padding(horizontal = 32.dp)
                            .rotate(rotateArrowState)
                            .size(24.dp)
                    )
                }
            }
        }
    }
}
