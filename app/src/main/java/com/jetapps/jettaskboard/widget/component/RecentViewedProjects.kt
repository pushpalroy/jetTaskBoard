package com.jetapps.jettaskboard.widget.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import com.jetapps.jettaskboard.feature.taskboard.R


@Composable
fun RecentViewedProjects() {
    Column(
        modifier = GlanceModifier
            .background(Color.Blue)
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProjectItemComponent(
            imageDrawableId = R.drawable.bg_board,
            projectName = "Praxis",
            projectOpenIssues = 32,
            lastUpdatedState = "5:01 PM"
        )

        Spacer(GlanceModifier.height(8.dp))

        ProjectItemComponent(
            imageDrawableId = R.drawable.bg_board,
            projectName = "Discord Clone",
            projectOpenIssues = 17,
            lastUpdatedState = "3:51 PM"
        )
    }
}