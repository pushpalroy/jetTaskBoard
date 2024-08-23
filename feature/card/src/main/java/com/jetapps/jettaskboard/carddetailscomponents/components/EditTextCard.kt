package com.jetapps.jettaskboard.carddetailscomponents.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.CardViewModel
import com.jetapps.jettaskboard.feature.card.R

@Composable
fun EditTextCard(viewModel: CardViewModel, isExpanded: Boolean = false) {
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
                .height(if (isExpanded) { 80.dp } else { 56.dp })
                .fillMaxWidth(),
            value = viewModel.inputValue.value,
            onValueChange = {
                viewModel.inputValue.value = it
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
