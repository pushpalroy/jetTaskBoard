package com.jetapps.jettaskboard.createboard.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@Composable
fun CreateformDropDown(
    text: String,
    modifier: Modifier,
    contentMap: Map<String, String>,
    initiallyOpened: Boolean = false,
    width: Dp
) {
    var isOpen by remember {
        mutableStateOf(initiallyOpened)
    }
    var dropdownWidth by remember {
        mutableStateOf(Size.Zero)
    }
    Box(modifier = Modifier.width(width), contentAlignment = Alignment.Center) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .onGloballyPositioned {
                    dropdownWidth = it.size.toSize()
                }
                .clickable {
                    isOpen = !isOpen
                }
        ) {

            Text(
                text = text,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Open or close the drop down",
                tint = Color.White,
                modifier = Modifier
                    .scale(1f, if (isOpen) -1f else 1f)
            )
        }

        DropdownMenu(expanded = isOpen,
            onDismissRequest = { isOpen = false },
            modifier = Modifier
                .background(color = Color.Black)
                .width(
                    with(LocalDensity.current) {
                        dropdownWidth.width.toDp()
                    }
                )
                .padding(horizontal = 16.dp)
        ) {
            contentMap.forEach { (itemKey, itemValue) ->
                DropdownMenuItem(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .background(color = Color.Cyan)
                        .padding(20.dp)
                ) {
                    Column(modifier = Modifier.padding(30.dp)) {
                        Text(text = itemKey, color = Color.White)
                        if (contentMap.values.isNotEmpty()) {
                            Text(
                                text = itemValue,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}