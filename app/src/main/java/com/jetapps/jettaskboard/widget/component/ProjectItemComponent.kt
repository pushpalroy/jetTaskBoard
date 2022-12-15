package com.jetapps.jettaskboard.widget.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle

@Composable
fun ProjectItemComponent(
    imageDrawableId: Int,
    projectName: String,
    projectOpenIssues: Int,
    lastUpdatedState: String
) {
    Row(
        modifier = GlanceModifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = GlanceModifier
                .defaultWeight()
                .size(40.dp)
                .padding(vertical = 4.dp, horizontal = 8.dp),
            provider = ImageProvider(imageDrawableId),
            contentDescription = "project photo",
        )

        Text(
            text = projectName,
            modifier = GlanceModifier
                .fillMaxWidth()
                .defaultWeight()
                .padding(vertical = 4.dp, horizontal = 12.dp),
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium)
        )

        Column(
            modifier = GlanceModifier
                .defaultWeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = projectOpenIssues.toString(),
                modifier = GlanceModifier,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
            )

            Spacer(modifier = GlanceModifier.height(4.dp))

            Text(
                text = "Open Issues",
                modifier = GlanceModifier,
                style = TextStyle(fontSize = 10.sp)
            )
        }
    }
}