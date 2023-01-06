package com.jetapps.jettaskboard.widget.composable

import android.widget.Space
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import com.jetapps.jettaskboard.R
import com.jetapps.jettaskboard.widget.component.CardsStatsRow
import com.jetapps.jettaskboard.widget.component.IssueCounterRow
import com.jetapps.jettaskboard.widget.component.LastUpdatedComponent
import com.jetapps.jettaskboard.widget.component.OnSiteFooterComponent

@Composable
fun HorizontalMediumRectangleComposable(
    lastUpdatedState: String
) {
    Column(
        modifier = GlanceModifier
            .appWidgetBackground()
            .background(ImageProvider(R.drawable.outer_widget_corner_background))
            .fillMaxSize(),
    ) {
        IssueCounterRow()
        Spacer(modifier = GlanceModifier.fillMaxWidth().height(2.dp).background(Color.DarkGray))
        CardsStatsRow()
        Spacer(modifier = GlanceModifier.fillMaxWidth().height(2.dp).background(Color.DarkGray))
        LastUpdatedComponent(lastUpdatedState = lastUpdatedState)
        OnSiteFooterComponent()
    }
}