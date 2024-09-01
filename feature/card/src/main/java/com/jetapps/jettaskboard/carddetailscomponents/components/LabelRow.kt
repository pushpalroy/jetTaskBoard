package com.jetapps.jettaskboard.carddetailscomponents.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.CardViewModel
import com.jetapps.jettaskboard.feature.card.R

@Composable
fun LabelRow(viewModel: CardViewModel) {
    val rotate = animateFloatAsState(targetValue = if (viewModel.isLabelRowClicked.value) 180f else 0f, tween(500))

    Column {
        Divider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("label")
                .clickable { viewModel.isLabelRowClicked.value = !(viewModel.isLabelRowClicked.value) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(16.dp),
                painter = painterResource(id = R.drawable.ic_label),
                contentDescription = "Leading Icon"
            )
            if (viewModel.selectedColors.isEmpty()) {
                Text(text = "Labels...", modifier = Modifier.weight(5f).testTag("label_text"))
            } else {
                Row(modifier = Modifier.weight(5f).testTag("labels_row")) {
                    viewModel.selectedColors.forEach {
                        Card(
                            modifier = Modifier
                                .height(36.dp)
                                .width(48.dp)
                                .padding(4.dp)
                                .testTag("label_card"),
                            backgroundColor = it,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                    }
                }
            }
            Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
                    .rotate(rotate.value),
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Trailing Icon"
            )
        }

        AnimatedVisibility(visible = viewModel.isLabelRowClicked.value) {
            Column {
                viewModel.labels.forEach { labelColor ->
                    LabelCheckBox(
                        color = labelColor.color,
                        isSelected = labelColor.isSelected,
                        onClick = {
                            labelColor.isSelected = !labelColor.isSelected
                            if (labelColor.isSelected && !viewModel.selectedColors.contains(labelColor.color)) {
                                viewModel.selectedColors.add(labelColor.color)
                            } else if (!labelColor.isSelected) {
                                viewModel.selectedColors.remove(labelColor.color)
                            }
                        }
                    )
                }
            }
        }
    }
}
