package com.jetapps.jettaskboard.carddetailscomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.feature.card.R

@Composable
fun EditTextCard(description: String?) {
    var inputvalue by remember { mutableStateOf(TextFieldValue(description ?: "")) }


    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(
            modifier = Modifier.padding(16.dp),
            painter = painterResource(id = R.drawable.ic_notes),
            contentDescription = "Notes Icon"
        )

        TextField(
            modifier = Modifier
                .background(Color.Transparent)
                .padding(end = 16.dp)
                .fillMaxWidth(),
            value = inputvalue,
            onValueChange = {
                inputvalue = it
            },
            placeholder = {
                Text(text = "Add card description...")
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}