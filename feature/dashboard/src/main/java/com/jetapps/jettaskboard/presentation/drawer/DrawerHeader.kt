package com.jetapps.jettaskboard.presentation.drawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.component.CoilAsyncImage

@Composable
fun DrawerHeader(
    onDrawerHeaderToggled: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ){
        Spacer(modifier = modifier.height(8.dp))
        CoilAsyncImage(imageShape = CircleShape, imageSize = 48.dp)

        Spacer(modifier = modifier.height(8.dp))
        Text(text = "Trello Org", fontWeight = FontWeight.Bold)

        Spacer(modifier = modifier.height(8.dp))
        Text(text = "@trello")

        Row(
            modifier = modifier.padding(all = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "board@trello.com",
                modifier = modifier.weight(1f)
            )

            IconButton(onClick = {
                onDrawerHeaderToggled()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop Down",
                    modifier = modifier.size(32.dp)
                )
            }
        }
    }
}