package com.jetapps.jettaskboard.carddetailscomponents

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.feature.card.R
import com.jetapps.jettaskboard.theme.LabelBlue
import com.jetapps.jettaskboard.theme.LabelGreen
import com.jetapps.jettaskboard.theme.LabelOrange
import com.jetapps.jettaskboard.theme.LabelPeach
import com.jetapps.jettaskboard.theme.LabelViolet
import com.jetapps.jettaskboard.theme.LabelYellow
import com.jetapps.jettaskboard.uimodel.LabelColor

@Composable
fun LabelRow(isExpanded: Boolean) {
    var isClicked by remember { mutableStateOf(false) }
    val labels = remember {
        mutableStateListOf(
            LabelColor(LabelGreen),
            LabelColor(LabelYellow),
            LabelColor(LabelPeach),
            LabelColor(LabelOrange),
            LabelColor(LabelViolet),
            LabelColor(LabelBlue),
        )
    }

    val selectedColors = remember {
        mutableStateListOf<Color>()
    }

    val rotate = animateFloatAsState(targetValue = if (isClicked) 180f else 0f, tween(500))

    Column {
        if (!isExpanded) {
            Divider()
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isClicked = !isClicked },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(16.dp),
                painter = painterResource(id = R.drawable.ic_label),
                contentDescription = "Leading Icon"
            )
            if (selectedColors.isEmpty()) {
                Text(text = "Labels...", modifier = Modifier.weight(5f))
            } else {
                Row(modifier = Modifier.weight(5f)) {
                    selectedColors.forEach {
                        Card(
                            modifier = Modifier
                                .height(36.dp)
                                .width(48.dp)
                                .padding(4.dp),
                            backgroundColor = it,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                    }

                }
            }
            if (!isExpanded) {
                Icon(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f)
                        .rotate(rotate.value),
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Trailing Icon"
                )
            }
        }

        AnimatedVisibility(visible = isClicked || isExpanded) {
            Column {
                labels.forEach { labelColor ->
                    LabelCheckBox(
                        color = labelColor.color,
                        isSelected = labelColor.isSelected,
                        onClick = {
                            labelColor.isSelected = !labelColor.isSelected
                            if (labelColor.isSelected && !selectedColors.contains(labelColor.color)) {
                                selectedColors.add(labelColor.color)
                            } else if (!labelColor.isSelected) {
                                selectedColors.remove(labelColor.color)
                            }
                        }
                    )
                }
            }
        }
    }
}