package com.jetapps.jettaskboard.createboard.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@Composable
fun CreateformDropDown(
    text: String,
    modifier: Modifier,
    contentMap: Map<String, String>,
    initiallyOpened: Boolean = false,
) {
    var isOpen by remember {
        mutableStateOf(initiallyOpened)
    }
    var dropdownWidth by remember {
        mutableStateOf(Size.Zero)
    }
    val alpha = animateFloatAsState(
        targetValue = if (isOpen) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300
        )
    )
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    dropdownWidth = it.size.toSize()
                }
        ) {

            Text(
                text = text,
                color = Color.White,
                fontSize = 12.sp
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Open or close the drop down",
                tint = Color.White,
                modifier = Modifier
                    .clickable {
                        isOpen = !isOpen
                    }
                    .scale(1f, if (isOpen) -1f else 1f)
            )
        }

        var screenSize = LocalConfiguration.current.screenWidthDp

        DropdownMenu(expanded = isOpen,
            onDismissRequest = { isOpen = false },
            modifier = Modifier  //TODO Have to check for the properties
                .height(100.dp)
                .width(
                    with(LocalDensity.current) {
                        dropdownWidth.width.toDp()
                    }
                )) {
            contentMap.forEach { (itemKey, itemValue) ->
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Column {
                        Text(text = itemKey)
                        if (contentMap.values.isNotEmpty()) {
                            Text(
                                text = itemValue,
                                modifier = Modifier.padding(top = 20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}