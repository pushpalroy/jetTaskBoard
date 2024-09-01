package com.jetapps.jettaskboard.carddetailscomponents.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuickActionChip(
    modifier: Modifier,
    leadingIcon: @Composable
    () -> Unit,
    title: String
) {
    Card(
        backgroundColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
        modifier = modifier
            .padding(start = 4.dp, end = 4.dp, bottom = 10.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            leadingIcon()
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = title,
                color = if (isSystemInDarkTheme()) Color.Black else Color.White,
                fontSize = 12.sp
            )
        }
    }
}
