package com.jetapps.jettaskboard.carddetailscomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ItemRow(leadingIcon: ImageVector, text: String, trailingIcon: ImageVector? = null) {
    Column {
        Divider()

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.padding(16.dp),
                imageVector = leadingIcon,
                contentDescription = "Leading Icon"
            )
            Text(text = text, modifier = Modifier.weight(5f))
            if (trailingIcon != null) {
                Icon(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f),
                    imageVector = trailingIcon,
                    contentDescription = "Trailing Icon"
                )
            }
        }
    }
}

@Composable
fun ItemRow(
    leadingIcon: Int,
    text: String,
    trailingIcon: ImageVector? = null,
    onClick: () -> Unit? = {}
) {
    Column {
        Divider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }, verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(16.dp),
                painter = painterResource(id = leadingIcon),
                contentDescription = "Leading Icon"
            )
            Text(text = text, modifier = Modifier.weight(5f))
            if (trailingIcon != null) {
                Icon(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f),
                    imageVector = trailingIcon,
                    contentDescription = "Trailing Icon"
                )
            }
        }
    }
}

@Composable
fun TimeItemRow(
    modifier: Modifier,
    icon: Int,
    topText: String,
    bottomText: String,
    onStartDateClick: () -> Unit,
    onDueDateClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box() {

        Column {
            Divider()

            Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
                Icon(
                    modifier = Modifier.padding(16.dp),
                    painter = painterResource(id = icon),
                    contentDescription = "Icon"
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = topText,
                        modifier = Modifier
                            .clickable { onStartDateClick() }
                            .padding(vertical = 16.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )

                    Divider()

                    Text(
                        text = bottomText,
                        modifier = Modifier
                            .clickable { onDueDateClick() }
                            .padding(vertical = 16.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }

        content()
    }
}