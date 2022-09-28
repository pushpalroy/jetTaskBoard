package com.jetapps.jettaskboard.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WorkSpaceItem(
    modifier: Modifier,
    workSpaceHeading: String?,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Outlined.Person,
            contentDescription = "WorkSpace Item"
        )

        workSpaceHeading?.let { safeWorkSpace ->
            Text(
                modifier = modifier
                    .weight(1f)
                    .padding(start = 24.dp),
                text = safeWorkSpace,
                fontSize = 16.sp,
                fontStyle = FontStyle.Normal
            )
        }

        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "WorkSpace Item"
        )
    }
}