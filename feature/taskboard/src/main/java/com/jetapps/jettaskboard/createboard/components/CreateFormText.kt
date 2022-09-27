package com.jetapps.jettaskboard.createboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CreateFormText(text: String, width:Dp) {
    Column(
        modifier = Modifier
            .width(width)
            .padding(top = 10.dp, start = 16.dp)
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = text, color = Color.White)
    }
}