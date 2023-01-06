package com.jetapps.jettaskboard.notifications.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterChip(chip: String) {
    var selected by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier.padding(5.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        border = if (selected) {
            BorderStroke(2.dp, Color.Black)
        } else {
            BorderStroke(2.dp, Color.White)
        },
        onClick = {selected = !selected},
        backgroundColor = if (selected) {
            Color.White
        } else {
            Color.Black
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp)
        ) {
            if (chip == "Me"){
                Icon(imageVector = Icons.Filled.Person, contentDescription = null)
            } else if (selected){
                Icon(imageVector = Icons.Filled.Check, contentDescription = null)
            }
            Text(text = chip, )
        }
    }
}