package com.jetapps.jettaskboard.createboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CreateFormEditText(hint:String, width: Dp) {
    var inputvalue by remember { mutableStateOf(TextFieldValue()) }
    Column(
        modifier = Modifier
            .width(width)
            .padding(start = 16.dp, end = 16.dp)
            .background(color = Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = inputvalue,
            onValueChange = { inputvalue = it },
            label = { Text(text = hint, color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.White),
            maxLines = 1,
            singleLine = true
        )
    }
}