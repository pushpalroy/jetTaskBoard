package com.jetapps.jettaskboard.widget.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.width
import com.jetapps.jettaskboard.R
import com.jetapps.jettaskboard.widget.component.CardStatComponent

@Composable
fun WidestSmallInfoWidget() {
    Row(
        modifier = GlanceModifier
            .appWidgetBackground()
            .background(ImageProvider(R.drawable.outer_widget_corner_background))
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(GlanceModifier.width(4.dp))

        CardStatComponent(
            count = 68,
            title = "Open\nIssues"
        )

        Spacer(GlanceModifier.width(4.dp))

        CardStatComponent(
            count = 12,
            title = "Due this\nWeek"
        )

        Spacer(GlanceModifier.width(4.dp))

        CardStatComponent(
            count = 183,
            title = "Reported\nby me"
        )

        Spacer(GlanceModifier.width(4.dp))

        CardStatComponent(
            count = 50,
            title = "Viewed\nRecently"
        )

        Spacer(GlanceModifier.width(4.dp))
    }
}