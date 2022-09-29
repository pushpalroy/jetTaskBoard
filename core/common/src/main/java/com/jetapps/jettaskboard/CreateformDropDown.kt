package com.jetapps.jettaskboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@Composable
fun CreateFormDropDown(
    headingText: String,
    modifier: Modifier = Modifier,
    contentMap: Map<String, String>,
    initiallyOpened: Boolean = false,
) {
    var isOpen by remember { mutableStateOf(initiallyOpened) }

    var dropDownWidth by remember { mutableStateOf(Size.Zero) }

    var selectMenuItem by remember {
        mutableStateOf("")
    }

    var selectedMenuItemHeading by remember {
        mutableStateOf(
            ""
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier.clickable { isOpen = !isOpen }
        ) {
            Text(
                modifier = modifier
                    .padding(horizontal = 8.dp),
                text = headingText,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .onGloballyPositioned { coordinates ->
                        dropDownWidth = coordinates.size.toSize()
                    }
            ) {
                Text(
                    text = selectedMenuItemHeading.ifEmpty { "Select from dropdown.. " },
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier
                )
                Icon(
                    imageVector = if (!isOpen)
                        Icons.Default.KeyboardArrowDown
                    else
                        Icons.Default.KeyboardArrowUp,
                    contentDescription = "Open or close the drop down",
                    tint = Color.White,
                    modifier = Modifier
                )
            }
        }

        Divider(
            color = MaterialTheme.colors.onBackground
        )

        DropdownMenu(
            expanded = isOpen,
            onDismissRequest = { isOpen = false },
            modifier = Modifier
                .width(
                    with(LocalDensity.current) {
                        dropDownWidth.width.toDp()
                    }
                )
                .padding(horizontal = 8.dp)
        ) {
            contentMap.forEach { (itemKey, itemValue) ->
                DropdownMenuItem(
                    onClick = {
                        isOpen = !isOpen
                        selectMenuItem = itemValue
                        selectedMenuItemHeading = itemKey
                    },
                    modifier = Modifier
                        .background(color = MaterialTheme.colors.background)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            modifier = modifier
                                .padding(all = 4.dp),
                            text = itemKey,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        if (contentMap.values.isNotEmpty()) {
                            Text(
                                modifier = modifier
                                    .padding(all = 4.dp),
                                text = itemValue,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp
                            )
                        }

                        Divider()
                    }
                }
            }
        }

        selectMenuItem.let { selectedItem ->
            if (selectedItem.isNotEmpty()){

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = selectMenuItem.ifEmpty { " " },
                    fontSize = 12.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun CreateBoardDropDown(
    headingText: String,
    modifier: Modifier = Modifier,
    contentMap: Map<Color, String>,
    initiallyOpened: Boolean = false,
) {
    var isOpen by remember { mutableStateOf(initiallyOpened) }

    var dropDownWidth by remember { mutableStateOf(Size.Zero) }

    var selectMenuItem by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier.clickable { isOpen = !isOpen }
        ) {
            Text(
                modifier = modifier
                    .padding(horizontal = 8.dp),
                text = headingText,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .onGloballyPositioned { coordinates ->
                        dropDownWidth = coordinates.size.toSize()
                    }
            ) {
                Text(
                    text = selectMenuItem.ifEmpty { "Select from dropdown.. " },
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier
                )
                Icon(
                    imageVector = if (!isOpen)
                        Icons.Default.KeyboardArrowDown
                    else
                        Icons.Default.KeyboardArrowUp,
                    contentDescription = "Open or close the drop down",
                    tint = Color.White,
                    modifier = Modifier
                )
            }
        }

        Divider(
            color = MaterialTheme.colors.onBackground
        )

        DropdownMenu(
            expanded = isOpen,
            onDismissRequest = { isOpen = false },
            modifier = Modifier
                .width(
                    with(LocalDensity.current) {
                        dropDownWidth.width.toDp()
                    }
                )
                .padding(horizontal = 8.dp)
        ) {
            contentMap.forEach { (itemKey, itemValue) ->
                DropdownMenuItem(
                    onClick = {
                        isOpen = !isOpen
                        selectMenuItem = itemValue
                    },
                    modifier = Modifier
                        .background(color = MaterialTheme.colors.background)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp),
                    ) {
                        Card(
                            modifier = modifier
                                .padding(all = 4.dp)
                                .size(16.dp),
                            shape = RoundedCornerShape(4.dp),
                            backgroundColor = itemKey as Color
                        ){}
                        if (contentMap.values.isNotEmpty()) {
                            Text(
                                modifier = modifier
                                    .padding(all = 4.dp),
                                text = itemValue,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}