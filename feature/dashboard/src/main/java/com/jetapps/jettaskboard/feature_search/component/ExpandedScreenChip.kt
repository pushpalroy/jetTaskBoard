package com.jetapps.jettaskboard.feature_search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExpandedScreenChip(
    name: String = "Empty Chip",
    isSelected: Boolean = false,
    onSelectionChanged: (String) -> Unit = {},
) {
    Surface(
        modifier = Modifier
            .padding(all = 4.dp),
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) MaterialTheme.colors.secondary else MaterialTheme.colors.primary
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 4.dp
                )
                .toggleable(
                    value = isSelected,
                    onValueChange = {
                        onSelectionChanged(name)
                    }
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = name.uppercase(),
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .padding(8.dp),
                fontWeight = FontWeight.SemiBold
            )

            if (isSelected) {
                Icon(
                    modifier = Modifier
                        .padding(4.dp),
                    imageVector = Icons.Default.Done,
                    contentDescription = "done_icon"
                )
            }
        }
    }
}

@Preview
@Composable
fun ExpandedScreenChipPreview() {
    ExpandedScreenChip(
        isSelected = true
    )
}