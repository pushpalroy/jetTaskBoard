package com.jetapps.jettaskboard.board

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetapps.jettaskboard.feature.taskboard.R

@Composable
fun TaskBoardExpandedScreenDrawerItem(
    title: String = "Change Background",
    icon: Painter,
    onItemClickListener: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onItemClickListener("") }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            modifier = Modifier.size(18.dp),
            contentDescription = "star",
            tint = Color(0xFFFFEB3B)
        )

        Text(
            text = title,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f),
            fontSize = 14.sp
        )
    }
}