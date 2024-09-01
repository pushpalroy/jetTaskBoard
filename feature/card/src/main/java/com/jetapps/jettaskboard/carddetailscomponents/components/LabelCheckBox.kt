package com.jetapps.jettaskboard.carddetailscomponents.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun LabelCheckBox(color: Color, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 8.dp)
            .selectable(
                selected = isSelected,
                onClick = onClick
            )
            .fillMaxWidth()
            .height(48.dp)
            .testTag("label_check_box"),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = color
    ) {
        if (isSelected) {
            Row(
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = "Color Selected",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .align(Alignment.CenterVertically)
                        .testTag("label_check_icon")
                )
            }
        }
    }
}
