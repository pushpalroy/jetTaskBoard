package com.jetapps.jettaskboard.widget.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.unit.ColorProvider
import com.jetapps.jettaskboard.R

@Composable
fun CardsStatsRow() {
    Row(
        modifier = GlanceModifier
            .background(ColorProvider(R.color.color_background_dark_secondary))
            .padding(vertical = 4.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(GlanceModifier.width(4.dp))

        CardStatComponent(
            count = 68,
            title = "Open\nIssues",
            backgroundDrawableId = R.drawable.outer_widget_corner_background
        )

        Spacer(GlanceModifier.width(4.dp))

        CardStatComponent(
            count = 12,
            title = "Due this\nWeek",
            backgroundDrawableId = R.drawable.outer_widget_corner_background
        )

        Spacer(GlanceModifier.width(4.dp))

        CardStatComponent(
            count = 183,
            title = "Reported\nby me",
            backgroundDrawableId = R.drawable.outer_widget_corner_background
        )

        Spacer(GlanceModifier.width(4.dp))

        CardStatComponent(
            count = 50,
            title = "Viewed\nRecently",
            backgroundDrawableId = R.drawable.outer_widget_corner_background
        )

        Spacer(GlanceModifier.width(4.dp))
    }
}